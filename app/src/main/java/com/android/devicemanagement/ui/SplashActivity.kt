package com.android.devicemanagement.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.android.devicemanagement.R
import com.android.devicemanagement.data.service.PreferenceService
import com.android.devicemanagement.ui.login.QrCodeGenerateActivity
import com.android.devicemanagement.ui.login.StartUpActivity
import com.android.devicemanagement.ui.login.fragment.SignupFragment
import com.android.devicemanagement.ui.login.viewmodel.LoginFragmentViewModel
import javax.inject.Inject

/**
 * Splash Activity for 3 seconds
 * @author Davinder syall
 */
class SplashActivity : AppCompatActivity() {

    // TODO Auto-generated method stub

 
    lateinit var prefference : PreferenceService

    companion object{
        const val SPLASH_TIME_OUT = 3000L
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        prefference = PreferenceService(this)
        navigateScreenAfterDelay()


    }

    fun navigateScreenAfterDelay(){
        Handler().postDelayed({
           
             if(!prefference.getString(R.string.deviceId).equals("")){
                 prefference.getString(R.string.deviceId)?.let {
                     val intent = Intent(this@SplashActivity, QrCodeGenerateActivity::class.java)
                     intent.putExtra(SignupFragment.DEVICE_ID, it)
                     startActivity(intent)
                 }
            }else {
                 startActivity(Intent(this@SplashActivity, StartUpActivity::class.java))

            }

            finish()

        }, SPLASH_TIME_OUT)
    }

}