package com.android.devicemanagement.ui.login

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.android.devicemanagement.R
import com.android.devicemanagement.ui.baseclass.BaseDaggerActivity
import kotlinx.android.synthetic.main.activity_start_up.*

class StartUpActivity : BaseDaggerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_up)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navhost_login.findNavController()
            .navigateUp() || super.onSupportNavigateUp()
    }
}
