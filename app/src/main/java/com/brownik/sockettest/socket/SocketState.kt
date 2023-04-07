package com.brownik.sockettest.socket

import org.json.JSONObject

interface SocketState {
    fun onConnect() {}
    fun onDisConnect() {}
    fun onError() {}

    fun onReceiveMessage(message: JSONObject) {}
    fun onSendMessage(message: JSONObject) {}
}