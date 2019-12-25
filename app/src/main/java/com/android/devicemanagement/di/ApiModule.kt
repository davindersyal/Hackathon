package com.android.devicemanagement.di

import com.android.devicemanagement.data.api.FireBaseNodes
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
class ApiModule {

    @Singleton
    @Provides
    @Named(FireBaseNodes.DEVICE_LIST_COLLECTION)
    fun fireDeviceListApi(firestore: FirebaseFirestore): CollectionReference {
        return firestore.collection(FireBaseNodes.DEVICE_LIST_COLLECTION)
    }

    @Singleton
    @Provides
    @Named(FireBaseNodes.ASSIGNED_USER_LIST)
    fun fireNewHorseAddApi(firestore: FirebaseFirestore): CollectionReference {
        return firestore.collection(FireBaseNodes.ASSIGNED_USER_LIST)
    }


    @Singleton
    @Provides
    @Named(FireBaseNodes.VERIFIED_DEVICE_CODE)
    fun fireVerifiedDeviceCodeApi(firestore: FirebaseFirestore): CollectionReference {
        return firestore.collection(FireBaseNodes.VERIFIED_DEVICE_CODE)
    }



}