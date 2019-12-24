package com.android.devicemanagement.ui.login.fragment

import android.os.Bundle
import android.view.View

import com.android.devicemanagement.R
import com.android.devicemanagement.databinding.FragmentLoginBinding
import com.android.devicemanagement.databinding.FragmentSignupBinding
import com.android.devicemanagement.ui.baseclass.BaseDaggerFragment


class SignupFragment : BaseDaggerFragment<FragmentSignupBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_signup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
