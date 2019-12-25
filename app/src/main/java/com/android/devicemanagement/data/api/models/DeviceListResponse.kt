package com.android.devicemanagement.data.api.models

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.android.parcel.Parcelize






//@PropertyName("qr_string") val qrString: String? = "",
//@PropertyName("check_out") val check_out: String? = "",
//@PropertyName("check_in") val check_in: String? = "",
//@PropertyName("device_token") var device_token: String? = "",

@Parcelize
data class DeviceListResponse(
    @PropertyName("id") var id: String = "",
    @PropertyName("device_name") val device_name: String? = "",
    @PropertyName("os") val os: String? = "",
    @PropertyName("device_status") val device_status: String?="",
    @PropertyName("verification_id") val verification_id: String?=""
): Parcelable




