package com.android.devicemanagement.data.api.models

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.android.parcel.Parcelize




@Parcelize
data class UserListResponse(
    @PropertyName("device_id") var device_id: String = "",
    @PropertyName("device_name") val device_name: String? = "",
    @PropertyName("id") val id: String? = "",
    @PropertyName("seat_number") val seat_number: String?="",
    @PropertyName("user_name") val user_name: String?=""
): Parcelable




