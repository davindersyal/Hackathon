package com.android.devicemanagement.ui.dashboard.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.devicemanagement.data.api.FireBaseNodes
import com.android.devicemanagement.data.api.models.DeviceListResponse
import com.android.devicemanagement.data.api.models.UserListResponse
import com.android.devicemanagement.ui.dashboard.AddDeviceActivity.Companion.UN_ASSIGNED
import com.android.devicemanagement.utils.BaseViewModel
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject
import javax.inject.Named

class DeviceItemModel @Inject constructor(
    @Named(FireBaseNodes.DEVICE_LIST_COLLECTION)
    private val deviceListCollection: CollectionReference,
    @Named(FireBaseNodes.ASSIGNED_USER_LIST)
    private val assignToUser: CollectionReference

) :
    BaseViewModel() {


    var userName: String = ""
    var deviceName: String = ""
    var os: String = ""
    var seatNumber: String = ""
    var check_out: String = ""
    var check_in: String = ""
    var operation_system :String =""

    var deviceListResponse = MutableLiveData<ArrayList<DeviceListResponse>>()

    var userListResponse = MutableLiveData<ArrayList<UserListResponse>>()




    fun deviceListMethod(isSuccess: (Boolean)->Unit) {
        var deviceId = deviceListCollection.document().id
        val addDeviceList = hashMapOf(
            "id" to deviceId,
            "device_name" to deviceName,
            "os" to operation_system,
            "device_status" to UN_ASSIGNED
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


    fun setMethod() {
        val deviceId = deviceListCollection.document().id
        val city = hashMapOf(
            "id" to deviceId,
            "device_name" to deviceName,
            "device_token" to userName,
            "os" to os,
            "qr_string" to seatNumber,
            "check_out" to check_out,
            "check_in" to check_in,
            "is_assigned" to false
        )

        deviceListCollection.document(deviceId)
            .set(city)
            .addOnSuccessListener { Log.e("check", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.e("sdfsd", "Error writing document", e) }

    }


    fun fetchDeviceList(deviceStatus: String){
        deviceListCollection.whereEqualTo("device_status",deviceStatus).get().addOnSuccessListener {
            val addObjects = arrayListOf<DeviceListResponse>()
            for (document in it) {
                if (document.exists()) {
                    addObjects.add(document.toObject(DeviceListResponse::class.java))
                }
            }
            deviceListResponse.postValue(addObjects)

        }.addOnFailureListener { e -> Log.e("sdfsd", "Error writing document", e) }
    }


    fun fetchUserList(deviceStatus: String, deviceId:String,list: (UserListResponse)->Unit){
        assignToUser.whereEqualTo("device_status",deviceStatus).whereEqualTo("device_id", deviceId).get().addOnSuccessListener {
            var addObjects :UserListResponse ?=null
            for (document in it) {
                if (document.exists()) {
                    addObjects =  document.toObject(UserListResponse::class.java)
                }
            }
            addObjects?.let {
                list(it)
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


    fun deleteItem(deviceId: String) {
        deviceListCollection.document(deviceId)
            .delete()
            .addOnSuccessListener { Log.e("check", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.e("sdfsd", "Error writing document", e) }

    }

    fun updateItem(deviceId: String) {
        deviceListCollection.document(deviceId)
            .update(convertHorsePojoToMap(deviceId))
            .addOnSuccessListener { Log.e("check", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.e("sdfsd", "Error writing document", e) }

    }

    private fun convertHorsePojoToMap(deviceId: String): Map<String, Any?> {
        return mapOf(
            "id" to deviceId,
            "device_name" to "check",
            "user_name" to "sdf",
            "seat_number" to seatNumber,
            "check_out" to check_out,
            "check_in" to check_in

        )
    }


}