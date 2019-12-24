package com.android.devicemanagement.ui.login.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

import com.android.devicemanagement.R
import com.android.devicemanagement.databinding.FragmentLoginBinding
import com.android.devicemanagement.data.network.NetworkState
import com.android.devicemanagement.ui.baseclass.BaseDaggerFragment
import com.android.devicemanagement.ui.dashboard.DashboardActivity
import com.android.devicemanagement.ui.login.viewmodel.LoginFragmentViewModel
import com.sdi.joyersmajorplatform.common.livedataext.throttleClicks
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseDaggerFragment<FragmentLoginBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_login

    val viewModel: LoginFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      subscribe(loginBtn.throttleClicks()) {
            var intent =Intent(requireContext(),DashboardActivity::class.java)
          startActivity(intent)
      }


    }
}
