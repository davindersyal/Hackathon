package com.android.savery.data.repository

import com.android.savery.data.api.AuthApi
import com.android.savery.data.api.models.LoginResponse
import com.android.savery.data.network.*
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val authApi: AuthApi
) {


    companion object {
        const val RESPONSE_SUCCESS = 1
    }

    fun signInApi(email :String, password:String): IRequest<Response<LoginResponse>> {
        return  NetworkRequest(appExecutors, object : IRetrofitNetworkRequestCallback<LoginResponse>
        {
            override fun createNetworkRequest(): Single<Response<LoginResponse>> {
                return authApi.apiUserLogin(email, password)
            }
       })
    }

}
