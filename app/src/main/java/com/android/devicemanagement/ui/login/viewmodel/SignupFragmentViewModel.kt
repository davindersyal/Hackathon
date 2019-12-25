package com.android.devicemanagement.ui.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.devicemanagement.R
import com.android.devicemanagement.data.api.FireBaseNodes
import com.android.devicemanagement.data.api.models.DeviceListResponse
import com.android.devicemanagement.data.api.models.UserListResponse
import com.android.devicemanagement.data.api.models.VerificationCodeResponse
import com.android.devicemanagement.data.service.PreferenceService
import com.android.devicemanagement.ui.dashboard.AddDeviceActivity.Companion.UN_ASSIGNED
import com.android.devicemanagement.ui.dashboard.DashboardActivity.Companion.DEVICE_ID
import com.android.devicemanagement.utils.BaseViewModel
import com.google.firebase.firestore.CollectionReference
import java.util.prefs.Preferences
import javax.inject.Inject
import javax.inject.Named

class SignupFragmentViewModel @Inject constructor(
    @Named(FireBaseNodes.DEVICE_LIST_COLLECTION)
    private val deviceListCollection: CollectionReference,
    @Named(FireBaseNodes.VERIFIED_DEVICE_CODE)
    private val verifiedDeviceCode: CollectionReference,
    private val preferences: PreferenceService
) :
    BaseViewModel() {


    var deviceName: String = ""
    var os: String = ""
    var operation_system: String = ""
    var deviceListResponse:  DeviceListResponse ?=null
    var udid :String =""
    var device_name = ""
    var deviceId:String =""
    var verificationCode =""



    fun deleteItem(deviceId: String,status:(Boolean)->Unit) {
        deviceListCollection.document(deviceId)
            .delete()
            .addOnSuccessListener {
                status(true)

            }
            .addOnFailureListener { e ->
                status(false)

            }

    }



    fun deviceListMethod(isSuccess: (Boolean) -> Unit) {
         deviceId = deviceListCollection.document().id
        val addDeviceList = hashMapOf(
            "id" to deviceId,
            "device_name" to deviceName,
            "os" to "Android",
            "device_status" to UN_ASSIGNED,
            "UUID"   to udid,
            "verification_id" to verificationCode
        )
        
        deviceListCollection.document(deviceId)
            .set(addDeviceList)
            .addOnSuccessListener {
                isSuccess(true)
            }
            .addOnFailureListener { e ->
                isSuccess(false)
            }

    }


    fun fetchCodeList(code: String,status:(String,Boolean,String)->Unit) {
        verifiedDeviceCode.whereEqualTo("code", code).get().addOnSuccessListener {
            var addObjects: VerificationCodeResponse? = null
            for (document in it) {
                if (document.exists()) {
                    addObjects = document.toObject(VerificationCodeResponse::class.java)
                }
            }
            addObjects?.let {
                if (it.status.equals(UN_ASSIGNED)) {
                    it.id?.let {
                        verificationCode = it
                        status("",true,it)
                    }
                }else{
                    status("Verification code already exist, Try again with new code",false,"")
                }
            }?:run{
                status("Invalid varification code",false,"")
            }

        }.addOnFailureListener { e -> Log.e("sdfsd", "Error writing document", e) }
    }


    fun fetchLiveData(verficationId :String,list:(String,Boolean)->Unit) {
        deviceListCollection.whereEqualTo("verification_id", verficationId).get().addOnSuccessListener { it->
            for (document in it) {
                if (document.exists()) {
                    deviceListResponse = document.toObject(DeviceListResponse::class.java)
                }
            }
            deviceListResponse?.let {
                it.verification_id?.let {verificationId->
                   deleteItem(it.id){
                       list(verificationId,true)
                   }
                }?:run{ list("",false) }
            }?:kotlin.run { list("",false) }


        }.addOnFailureListener { e ->  }
    }


    fun updateverifiedDeviceUser(verificationCode:String,message:(Boolean)->Unit){
        verifiedDeviceCode.document(verificationCode)
            .update(mapOf("status" to "unassigned"))
            .addOnSuccessListener {
                message(true)
            }.addOnFailureListener { e ->
                message(false)
            }
    }



     fun updateVerificationItem(deviceId: String,status:(String,Boolean,String)->Unit) {
        verifiedDeviceCode.document(deviceId)
            .update(convertHorsePojoToMap())
            .addOnSuccessListener {
                preferences.putString(R.string.deviceId,deviceId)
                status("Congratulation! your device successfully registered",true,"")
            }.addOnFailureListener { e ->
                status("Something went wrong, Try again with new code",false,"")
            }
    }

    private fun convertHorsePojoToMap(): Map<String, Any?> {
        return mapOf("status" to "assigned")
    }

}