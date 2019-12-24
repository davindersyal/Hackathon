package com.android.devicemanagement.data.api.models

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DeviceListResponse(
    @PropertyName("id") var id: String = "",
    @PropertyName("device_name") var device_name: String? = "",
    @PropertyName("user_name") val user_name: String? = "",
    @PropertyName("IpAddress") val IpAddress: String? = "",
    @PropertyName("seat_number") val seat_number: String? = "",
    @PropertyName("check_out") val check_out: String? = "",
    @PropertyName("check_in") val check_in: String? = ""
): Parcelable







