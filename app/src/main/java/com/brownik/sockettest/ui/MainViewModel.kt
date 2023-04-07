package com.brownik.sockettest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brownik.sockettest.model.ResponseAuth
import com.brownik.sockettest.model.ResponseOneToOne
import com.brownik.sockettest.socket.SocketConstants
import com.brownik.sockettest.socket.SocketSession
import com.brownik.sockettest.socket.SocketStateListener
import com.google.gson.Gson
import org.json.JSONObject

class MainViewModel: ViewModel() {

    private val socketSession = SocketSession()

    private val _socketConnect = MutableLiveData(false)
    val socketConnect: LiveData<Boolean> = _socketConnect

    private val _authResult = MutableLiveData(false)
    val authResult: LiveData<Boolean> = _authResult

    fun requestAuth(cmd: String, memNo: String) {
        val data = JSONObject().apply {
            put(SocketConstants.MEM_NO, memNo.toInt())
        }
        socketSession.send(cmd, SocketConstants.RQ_USER_AUTH, data)
    }

    fun requestSocketConnect() {
        socketSession.connect()
    }

    fun requestSocketDisconnect() {
        socketSession.disConnect()
    }

    fun setSocketListener() {
        socketSession.setSocketListener(socketListener)
    }

    private val socketListener = object : SocketStateListener {
        override fun onConnect() {
            _socketConnect.postValue(true)
        }

        override fun onDisConnect() {
            _socketConnect.postValue(false)
        }

        override fun onReceiveMessage(message: String) {
            if(_authResult.value!!) {
                val data = Gson().fromJson(message, ResponseOneToOne::class.java)
            } else {
                val data = Gson().fromJson(message, ResponseAuth::class.java)
                if (data.data.result == 1) _authResult.postValue(true)
            }
        }
    }
}