package com.android.savery.data.api

import com.android.savery.data.api.models.LoginResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {
   
    companion object {
        const val MODULE_PATH = "portal_new/api/"
    }



    @POST("user_login.php/")
    @FormUrlEncoded
    fun apiUserLogin(
        @Field("email") username: String,
        @Field("password") password: String
    ): Single<Response<LoginResponse>>



}