package com.brownik.sockettest.feature_sign.ui

import android.os.Bundle
import android.view.animation.AnimationUtils
import com.brownik.sockettest.common_base.ui.BaseActivity
import com.brownik.sockettest.common_util.extension.replaceFragment
import com.brownik.sockettest.feature_sign.R
import com.brownik.sockettest.feature_sign.databinding.ActivitySignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>() {

    private var signUpFragment: SignUpFragment? = null

    override fun createBinding() = ActivitySignInBinding.inflate(layoutInflater)

    override fun initActivity(savedInstanceState: Bundle?) {
        setOnClickListener()
    }

    private fun setOnClickListener() = with(binding) {
        btnSignIn.setOnClickListener {

        }
        btnGoSignUp.setOnClickListener {
            signUpFragment = SignUpFragment().apply {
                setCallback { state ->
                    when (state) {
                        SignUpFragment.ON_ADDED -> {
                            val anim = AnimationUtils.loadAnimation(this@SignInActivity, R.anim.bottom_in)
                            etcContainer.startAnimation(anim)
                        }
                        SignUpFragment.ON_REMOVE -> {}
                    }
                }
            }
            replaceFragment(R.id.etcContainer, signUpFragment)
        }
    }
}