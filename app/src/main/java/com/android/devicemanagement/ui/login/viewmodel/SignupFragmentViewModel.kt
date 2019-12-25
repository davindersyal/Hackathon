package com.android.devicemanagement.ui.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.devicemanagement.data.api.FireBaseNodes
import com.android.devicemanagement.data.api.models.DeviceListResponse
import com.android.devicemanagement.data.api.models.UserListResponse
import com.android.devicemanagement.data.api.models.VerificationCodeResponse
import com.android.devicemanagement.ui.dashboard.AddDeviceActivity.Companion.UN_ASSIGNED
import com.android.devicemanagement.utils.BaseViewModel
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject
import javax.inject.Named

class SignupFragmentViewModel @Inject constructor(
    @Named(FireBaseNodes.DEVICE_LIST_COLLECTION)
    private val deviceListCollection: CollectionReference,
    @Named(FireBaseNodes.VERIFIED_DEVICE_CODE)
    private val verifiedDeviceCode: CollectionReference

) :
    BaseViewModel() {


    var deviceName: String = ""
    var os: String = ""
    var operation_system: String = ""
    var deviceListResponse = MutableLiveData<ArrayList<DeviceListResponse>>()
    var udid :String =""
    var device_name = ""
    var deviceId:String =""




     fun deviceListMethod(isSuccess: (Boolean) -> Unit) {
         deviceId = deviceListCollection.document().id
        val addDeviceList = hashMapOf(
            "id" to deviceId,
            "device_name" to deviceName,
            "os" to "Android",
            "device_status" to UN_ASSIGNED,
            "UUID"   to udid
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


    fun fetchLiveData() {
        deviceListCollection.get().addOnSuccessListener {
            val addObjects = arrayListOf<DeviceListResponse>()
            for (document in it) {
                if (document.exists()) {
                    addObjects.add(document.toObject(DeviceListResponse::class.java))
                }
            }
            deviceListResponse.postValue(addObjects)

        }.addOnFailureListener { e -> Log.e("sdfsd", "Error writing document", e) }
    }



     fun updateVerificationItem(deviceId: String,status:(String,Boolean,String)->Unit) {
        verifiedDeviceCode.document(deviceId)
            .update(convertHorsePojoToMap())
            .addOnSuccessListener {
                status("Congratulation! your device successfully registered",true,"")
            }.addOnFailureListener { e ->
                status("Something went wrong, Try again with new code",false,"")
            }
    }

    private fun convertHorsePojoToMap(): Map<String, Any?> {
        return mapOf("status" to "assigned")
    }

}