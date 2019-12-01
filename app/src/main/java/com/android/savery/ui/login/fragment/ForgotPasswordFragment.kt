package com.android.savery.ui.login.fragment

import android.os.Bundle
import android.view.View

import com.android.savery.R
import com.android.savery.databinding.FragmentForgotpasswordBinding
import com.android.savery.databinding.FragmentLoginBinding
import com.android.savery.ui.baseclass.BaseDaggerFragment


class ForgotPasswordFragment : BaseDaggerFragment<FragmentForgotpasswordBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_forgotpassword

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
