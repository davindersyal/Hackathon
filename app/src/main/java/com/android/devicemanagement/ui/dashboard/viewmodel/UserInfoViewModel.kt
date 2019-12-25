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
               isSuccess(true)
            }
            .addOnFailureListener { e ->
                isSuccess(false)
            }
    }

    fun updateItem(deviceId: String,status:(Boolean)->Unit) {
        deviceListCollection.document(deviceId)
            .update(convertHorsePojoToMap())
            .addOnSuccessListener {
             status(true)
            }
            .addOnFailureListener { e ->
                status(false)
            }
        
    }


    fun updateUserList(status:(Boolean)->Unit) {
        assignToUser.document(userId)
            .update(convertHorsePojoToMap())
            .addOnSuccessListener {
                status(true)
            }
            .addOnFailureListener { e ->
                status(false)
            }

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