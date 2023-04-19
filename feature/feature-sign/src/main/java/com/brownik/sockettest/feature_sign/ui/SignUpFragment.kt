package com.brownik.sockettest.feature_sign.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.brownik.sockettest.common_base.ui.BaseFragment
import com.brownik.sockettest.feature_sign.databinding.FragmentSignUpBinding

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    companion object {
        private val TAG = this::class.java.simpleName
        val ON_ADDED = "onAdded"
        val ON_REMOVE = "onRemove"
    }

    override val enableBackPressed: Boolean = true

//    var callback: ((String) -> Unit)? = null

    override fun createFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentSignUpBinding.inflate(inflater, container, false)

    override fun initFragment(savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
//        callback?.invoke(ON_ADDED)
    }

    fun setCallback(callback: ((String) -> Unit)? = null) {
//        this.callback = callback
    }
}