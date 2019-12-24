package com.android.devicemanagement.ui.dashboard.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.devicemanagement.data.api.FireBaseNodes
import com.android.devicemanagement.data.api.models.DeviceListResponse
import com.android.devicemanagement.utils.BaseViewModel
import com.android.devicemanagement.utils.QuerySnapshotLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.QuerySnapshot
import com.sdi.joyersmajorplatform.common.livedataext.map
import javax.inject.Inject
import javax.inject.Named

class DeviceItemModel @Inject constructor(@Named(FireBaseNodes.DEVICE_LIST_COLLECTION)
                                          private val deviceListCollection: CollectionReference) :
    BaseViewModel() {



    var userName :String=""
    var deviceName:String=""
    var IpAddress : String=""
    var seatNumber :String =""
    var check_out:String=""
    var check_in:String=""

    var deviceListResponse = MutableLiveData<ArrayList<DeviceListResponse>>()


     fun setMethod(){
         var deviceId =deviceListCollection.document().id

        val city = hashMapOf(
            "id" to deviceId,
            "device_name" to deviceName,
            "user_name" to userName,
            "IpAddress" to IpAddress,
            "seat_number" to seatNumber,
            "check_out" to check_out,
            "check_in" to check_in
            )

        deviceListCollection.document(deviceId)
            .set(city)
            .addOnSuccessListener { Log.e("check", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.e("sdfsd", "Error writing document", e) }

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


    fun deleteItem(deviceId :String){
        deviceListCollection.document(deviceId)
            .delete()
            .addOnSuccessListener { Log.e("check", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.e("sdfsd", "Error writing document", e) }

    }

    fun updateItem(deviceId :String){

        deviceListCollection.document(deviceId)
            .update(convertHorsePojoToMap(deviceId))
            .addOnSuccessListener { Log.e("check", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.e("sdfsd", "Error writing document", e) }

    }

    fun convertHorsePojoToMap(deviceId: String): Map<String, Any?> {
        return mapOf(
            "id" to deviceId,
            "device_name" to "check",
            "user_name" to "sdf",
            "IpAddress" to IpAddress,
            "seat_number" to seatNumber,
            "check_out" to check_out,
            "check_in" to check_in

        )
    }




}