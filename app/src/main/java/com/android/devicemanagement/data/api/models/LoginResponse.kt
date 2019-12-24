package com.android.devicemanagement.data.api.models

data class LoginResponse(
    val body: Body,
    val status: Status
){

    data class Status(
        val code: Int,
        val message: String
    )

    data class Body(
        val app_name: String,
        val app_version: String,
        val country: String,
        val created_date: String,
        val device_token: String,
        val device_type: String,
        val email: String,
        val id: String,
        val latitude: String,
        val longitude: String,
        val mobile: String,
        val name: String,
        val newsletter: String,
        val password: String,
        val referred: String,
        val secure_key: String,
        val social_id: String,
        val social_type: String,
        val status: String,
        val time_zone: String,
        val user_image: String,
        val user_type: String
    )

}
