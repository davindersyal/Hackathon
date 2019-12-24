package com.android.devicemanagement.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.android.devicemanagement.R
import com.android.devicemanagement.ui.login.StartUpActivity

/**
 * Splash Activity for 3 seconds
 * @author Davinder syall
 */
class SplashActivity : AppCompatActivity() {

    // TODO Auto-generated method stub

//    @Inject
//    lateinit var prefference : PreferenceService

    companion object{
        const val SPLASH_TIME_OUT = 3000L
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
       // prefference = PreferenceService(this)
        navigateScreenAfterDelay()


    }

    fun navigateScreenAfterDelay(){
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, StartUpActivity::class.java))
            finish()
            //            if(!prefference.getString(R.string.userId).equals("")){
//                val intent = Intent(this@SplashActivity, DashboardActivity::class.java)
//                startActivity(intent)
//            }else {
//                val intent = Intent(this@SplashActivity, StartUpActivity::class.java)
//                startActivity(intent)
//            }

        }, SPLASH_TIME_OUT)
    }

}