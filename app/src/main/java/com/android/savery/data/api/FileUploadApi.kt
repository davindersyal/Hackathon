package com.android.savery.data.api

import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface FileUploadApi {
   
    companion object {
        const val MODULE_PATH = "uploadFile/"
    }
    
//    @Multipart
//    @POST("uploadProfileImage/")
//    fun uploadImage(@Part file: MultipartBody.Part): Single<FileUploadModel>
//
//    @Multipart
//    @POST("uploadAudioFile/")
//    fun uploadAudioFile(@Part file: MultipartBody.Part): Single<FileUploadModel>
//
//    @Multipart
//    @POST("uploadJoyFile/")
//    fun uploadJoyFile(@Header("Authorization") authHeader: String, @Part file: MultipartBody.Part, @Part("userId") uuid: RequestBody): Single<JoyFileUploadMOdel>
//


    


}