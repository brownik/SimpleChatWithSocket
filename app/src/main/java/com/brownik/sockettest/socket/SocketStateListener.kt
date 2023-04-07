package com.brownik.sockettest.socket


interface SocketStateListener {
    fun onConnect() {}
    fun onDisConnect() {}
    fun onError() {}

    fun onReceiveMessage(message: String) {}
    fun onSendMessage(message: String) {}
}