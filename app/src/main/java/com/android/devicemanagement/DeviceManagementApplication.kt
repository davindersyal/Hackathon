package com.android.devicemanagement

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import com.android.devicemanagement.di.DaggerAppComponent
import dagger.android.support.DaggerApplication

class DeviceManagementApplication : DaggerApplication() {

    override fun applicationInjector() =
        DaggerAppComponent.builder()
        .application(this)
        .build()


    //var info =  File()
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


    override fun onCreate() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
        super.onCreate()
       // instance = this
    }

//    fun checkIfHasNetwork(): Boolean {
//        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo = cm.activeNetworkInfo
//        return networkInfo != null && networkInfo.isConnected
//
//    }


//    companion object {
//        val TAG = JoyerApplication::class.java.simpleName
//        var instance: JoyerApplication? = null
//            private set
//
//        fun hasNetwork(): Boolean {
//            return instance!!.checkIfHasNetwork()
//        }
//    }
}