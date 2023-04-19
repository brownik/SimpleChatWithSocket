package com.brownik.sockettest.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.brownik.sockettest.R
import com.brownik.sockettest.common_base.ui.BaseActivity
import com.brownik.sockettest.databinding.ActivityMainBinding
import com.brownik.sockettest.feature_sign.ui.SignInActivity
import com.brownik.sockettest.socket.SocketConstants
import com.brownik.sockettest.util.ShowToast

class MainActivity : BaseActivity<ActivityMainBinding>() {


    private val mainViewModel by viewModels<MainViewModel>()

    override fun createBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initActivity(savedInstanceState: Bundle?) {
        binding.viewModel = mainViewModel
        initViewModel()
        setSocketListener()
        addOnClickListener()
        val intent = Intent(this, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    private fun initViewModel() = with(mainViewModel) {
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