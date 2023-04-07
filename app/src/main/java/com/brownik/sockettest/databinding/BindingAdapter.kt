package com.brownik.sockettest.databinding

import android.widget.Button
import androidx.databinding.BindingAdapter
import com.brownik.sockettest.R

object BindingAdapter {

    @BindingAdapter("setText")
    @JvmStatic
    fun setText(view: Button, division: Boolean) {
        val text =
            if (division) R.string.btn_disconnect
            else R.string.btn_connect
        view.text = view.context.getString(text)
    }
}