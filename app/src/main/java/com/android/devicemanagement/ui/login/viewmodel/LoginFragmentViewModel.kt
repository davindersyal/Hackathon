package com.android.devicemanagement.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.devicemanagement.data.api.models.LoginResponse
import com.android.devicemanagement.data.network.NetworkState
import com.android.devicemanagement.utils.BaseViewModel
import com.sdi.joyersmajorplatform.common.livedataext.postUpdate
import javax.inject.Inject

class LoginFragmentViewModel @Inject constructor() :
    BaseViewModel(){

   var loginResponse = MutableLiveData<LoginResponse>()


//    fun LoginApiHit(): LiveData<NetworkState> {
//        val request = userRepository.signInApi("dev.test@gmail.com", "123456").also { it ->
//            subscribe(it.request) {
//                it.body()?.let {
//                    loginResponse.postUpdate(it)
//                }
//            }
//        }
//        return request.networkState
//    }


}