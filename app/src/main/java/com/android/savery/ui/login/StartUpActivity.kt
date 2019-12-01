package com.android.savery.ui.login

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.android.savery.R
import com.android.savery.ui.baseclass.BaseDaggerActivity
import dagger.android.support.DaggerAppCompatActivity
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
