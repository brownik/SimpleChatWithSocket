package com.brownik.sockettest.common_base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import kotlin.system.exitProcess

abstract class BaseActivity<Binding : ViewDataBinding> : AppCompatActivity() {

    companion object {
        private val TAG = this::class.java.simpleName
    }

    protected val binding: Binding by lazy { createBinding() }

    protected abstract fun createBinding(): Binding

    protected open fun initActivity(savedInstanceState: Bundle?) = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        initActivity(savedInstanceState)
    }

    open fun finishActivity() {
        if (!isFinishing) finish()
    }

    fun finishApplication() {
        if (!isFinishing) {
            finishAffinity()
            exitProcess(0)
        }
    }
}