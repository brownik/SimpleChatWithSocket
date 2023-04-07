package com.brownik.sockettest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brownik.sockettest.socket.SocketConstants
import com.brownik.sockettest.socket.SocketSession
import org.json.JSONObject

class MainActivityViewModel: ViewModel() {

    private val socketSession = SocketSession()

    private val _authResult = MutableLiveData(false)
    val authResult: LiveData<Boolean> = _authResult

    fun requestAuth(chatType: String) {
        val data = JSONObject().apply {

        }
        socketSession.send(chatType, SocketConstants.CHAT_TYPE_LOBBY, data)
    }
}