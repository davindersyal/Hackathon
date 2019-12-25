package com.android.devicemanagement.ui.dashboard.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.devicemanagement.data.api.FireBaseNodes
import com.android.devicemanagement.data.api.models.DeviceListResponse
import com.android.devicemanagement.ui.dashboard.AddDeviceActivity.Companion.ASSIGNED
import com.android.devicemanagement.ui.dashboard.AddDeviceActivity.Companion.UN_ASSIGNED
import com.android.devicemanagement.utils.BaseViewModel
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject
import javax.inject.Named

class UserInfoViewModel @Inject constructor(
    @Named(FireBaseNodes.ASSIGNED_USER_LIST)
    private val assignToUser: CollectionReference,
    @Named(FireBaseNodes.DEVICE_LIST_COLLECTION)
    private val deviceListCollection: CollectionReference
) :
    BaseViewModel() {


    var userName: String = ""
    var deviceName: String = ""
    var os: String = ""
    var seatNumber:String=""

    var deviceId :String =""
    var deviceStatus:String=""
    var userId:String=""


   // var deviceListResponse = MutableLiveData<ArrayList<DeviceListResponse>>()


    fun assignUserList(isSuccess: (Boolean)->Unit) {
        val id = assignToUser.document().id
        val addDeviceList = hashMapOf(
            "id" to  id,
            "device_id" to deviceId,
            "seat_number" to seatNumber,
            "device_name" to deviceName,
            "user_name" to userName,
            "device_status" to ASSIGNED
        )

        assignToUser.document(id)
            .set(addDeviceList)
            .addOnSuccessListener {
                updateItem(deviceId)
            }
            .addOnFailureListener { e ->
                isSuccess(false)
            }
    }

    private fun updateItem(deviceId: String) {
        deviceListCollection.document(deviceId)
            .update(convertHorsePojoToMap())
            .addOnSuccessListener { Log.e("check", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.e("sdfsd", "Error writing document", e) }

    }


    fun updateUserList() {
        assignToUser.document(userId)
            .update(convertHorsePojoToMap())
            .addOnSuccessListener { updateItem(deviceId) }
            .addOnFailureListener { e -> Log.e("sdfsd", "Error writing document", e) }

    }


    private fun convertHorsePojoToMap(): Map<String, Any?> {
        return mapOf(
            "device_status" to statusValidationCheck()
        )
    }
    
    private fun statusValidationCheck(): String{
      return  if(deviceStatus.equals(ASSIGNED,ignoreCase = true))
            UN_ASSIGNED
        else
            ASSIGNED
    }

 
  

}