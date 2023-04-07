package com.brownik.sockettest.util

import android.content.Context
import android.widget.Toast

object ShowToast {

    fun short(context: Context, data: String) {
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show()
    }

    fun long(context: Context, data: String) {
        Toast.makeText(context, data, Toast.LENGTH_LONG).show()
    }
}