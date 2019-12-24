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
    @Named(FireBaseNodes.NEW_HORSE_COLLECTION)
    fun fireNewHorseAddApi(firestore: FirebaseFirestore): CollectionReference {
        return firestore.collection(FireBaseNodes.NEW_HORSE_COLLECTION)
    }
}