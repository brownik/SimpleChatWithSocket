package com.brownik.sockettest.socket

import android.annotation.SuppressLint
import com.brownik.sockettest.util.BaseLog
import io.socket.client.IO
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.emitter.Emitter
import io.socket.engineio.client.Transport
import io.socket.engineio.client.transports.WebSocket
import okhttp3.OkHttpClient
import org.json.JSONObject
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class SocketSession {

    companion object {
        private val TAG = SocketSession::class.java.simpleName
        private const val SERVER_URL = "http://61.80.148.23:3001/"
    }

    private var socket: Socket? = null
    private var listener: SocketStateListener? = null

    fun connect() {
        val option = IO.Options().apply {
            forceNew = true
            reconnection = false
            webSocketFactory = getSslSocketClient()
            transports = arrayOf(WebSocket.NAME)
            timeout = 10000L
        }

        val onTransport = Emitter.Listener { args ->
            val transport = args[0] as Transport
            transport.on(Transport.EVENT_REQUEST_HEADERS) {
                val headers = it[0] as MutableMap<String, List<String>>
                headers["origin"] = listOf(SERVER_URL)
            }
        }

        socket = IO.socket(SERVER_URL, option).apply {
            io().on(Manager.EVENT_TRANSPORT, onTransport)
            on(Socket.EVENT_CONNECT, onConnect)
            on(Socket.EVENT_CONNECT_ERROR, onError)
            on(Socket.EVENT_DISCONNECT, onDisconnect)
            on(Manager.EVENT_ERROR, onError)
            on(SocketConstants.CHAT_TYPE_LOBBY, onMessageReceiver)
        }

        socket?.connect()
    }

    fun send(chatType: String, cmd: String, data: JSONObject) {
        socket?.takeIf { it.connected() }?.let {
            val sendData = JSONObject().apply {
                put(SocketConstants.COMMON_CMD, cmd)
                put(SocketConstants.COMMON_DATA, data)
            }
            it.emit(chatType, sendData)
        }
    }

    fun disConnect() {
        socket?.disconnect()
        socket = null
    }

    fun setSocketListener(listener: SocketStateListener) {
        this.listener = listener
    }

    private val onMessageReceiver = Emitter.Listener {
        val data = it.first().toString()
        BaseLog.d("onMessageReceiver: $data")
        listener?.onReceiveMessage(data)
    }

    private val onConnect = Emitter.Listener {
        BaseLog.d("onConnect: $it")
        listener?.onConnect()
    }

    private val onDisconnect = Emitter.Listener {
        BaseLog.d("onDisconnect: $it")
        listener?.onDisConnect()
    }

    private val onError = Emitter.Listener {
        it.takeIf { it.isNotEmpty() }?.run {
            BaseLog.d("onError: ${it.first()}")
        } ?: run {
            BaseLog.d("onError")
        }
        listener?.onError()
    }

    private val trustAllCerts = arrayOf<TrustManager>(
        @SuppressLint("CustomX509TrustManager")
        object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }
        }
    )

    private fun getSslSocketClient(): OkHttpClient {
        val hostnameVerifier = HostnameVerifier { _, _ -> true }
        val sslContext = SSLContext.getInstance(SocketConstants.SOCKET_PROTOCOL)
        sslContext.init(null, trustAllCerts, null)
        val sslSocketFactory = sslContext.socketFactory
        val trustManager = trustAllCerts[0] as X509TrustManager

        return OkHttpClient.Builder().hostnameVerifier(hostnameVerifier)
            .sslSocketFactory(sslSocketFactory, trustManager).build()
    }
}