package com.brownik.sockettest.util

import android.util.Log

object BaseLog {

    const val BASE_LOG = "BaseLog"

    fun d(data: String) {
        Log.d(BASE_LOG, data)
    }
}