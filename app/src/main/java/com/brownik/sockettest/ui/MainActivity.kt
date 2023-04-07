package com.brownik.sockettest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.brownik.sockettest.databinding.ActivityMainBinding
import io.socket.client.Socket

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val serverUrl = "http://61.80.148.23:3001/"
    private var socket: Socket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        addOnClickListener()
    }

    private fun addOnClickListener() = with(binding) {
        btnSocket.setOnClickListener {

        }

        btnSend.setOnClickListener {

        }
    }

    private fun showToast(text: String) {
        runOnUiThread {
            Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
        }
    }
}