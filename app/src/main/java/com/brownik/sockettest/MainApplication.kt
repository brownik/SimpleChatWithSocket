package com.brownik.sockettest

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : MultiDexApplication() {

    private val tag = this::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        initFirebase()
    }

    private fun initFirebase() {

    }
}