package com.android.devicemanagement.data.repository

//import com.sdi.joyersmajorplatform.R
//import com.sdi.joyersmajorplatform.data.api.models.*
//import com.sdi.joyersmajorplatform.network.*
//import io.reactivex.Single
//import javax.inject.Inject

//class JoyRepository @Inject constructor(
//    private val appExecutors: AppExecutors,
//    private val joyApi: JoyApi
//) {
//
//
//    fun createJoyRespository(
//        postData: JoyerCreatedModel,
//        token: String
//    ): IRequest<JoyStatusCreatedResponse>{
//        return  NetworkRequest(appExecutors, object : INetworkRequestCallback<JoyStatusCreatedResponse>
//        {
//            /**
//             * Called if [isSuccess] returns false.
//             * @return A [NetworkState] representing the error.
//             */
//            override fun getErrorState(response: JoyStatusCreatedResponse): NetworkState {
//                return NetworkState.error(response.msg, R.string.network_error_unknown)
//            }
//
//            /**
//             * Create and return the network request.
//             */
//            override fun createNetworkRequest(): Single<JoyStatusCreatedResponse> {
//                return joyApi.createJoyApi(token,postData)
//            }
//
//            /**
//             * Filter http errors.
//             * @return True if successful, false otherwise.
//             */
//            override fun isSuccess(response: JoyStatusCreatedResponse): Boolean {
//                return response.success
//            }
//
//        })
//
//    }
//
//
//}