package com.brownik.sockettest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.brownik.sockettest.R
import com.brownik.sockettest.databinding.ActivityMainBinding
import com.brownik.sockettest.socket.SocketConstants
import com.brownik.sockettest.socket.SocketStateListener
import com.brownik.sockettest.util.ShowToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var socketListener: SocketStateListener? = null
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this@MainActivity
        binding.viewModel = mainViewModel

        setViewModel()
        setSocketListener()
        addOnClickListener()
    }

    private fun setViewModel() = with(mainViewModel) {
        socketConnect.observe(this@MainActivity, Observer {
            var text = 0
            if (it) {
                text = R.string.socket_connect
                window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
            } else {
                text = R.string.socket_disconnect
                window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
            }
            ShowToast.short(this@MainActivity, getString(text))
        })

        authResult.observe(this@MainActivity, Observer {
            if (it) addFragment()
        })
    }

    private fun addOnClickListener() = with(binding) {
        btnSocket.setOnClickListener {
            if (mainViewModel.socketConnect.value == false) mainViewModel.requestSocketConnect()
            else mainViewModel.requestSocketDisconnect()
        }

        btnSend.setOnClickListener {
            if (mainViewModel.authResult.value == false)
                mainViewModel.requestAuth(SocketConstants.CHAT_TYPE_LOBBY, etMemNo.text.toString())
        }
    }

    private fun setSocketListener() {
        mainViewModel.setSocketListener()
    }

    private fun addFragment() {

    }
}