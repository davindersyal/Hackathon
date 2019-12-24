package com.android.devicemanagement.di

import android.app.Application
import android.content.Context
import com.android.devicemanagement.DeviceManagementApplication
import com.android.devicemanagement.data.service.PreferenceService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [UiModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(application: DeviceManagementApplication): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideContext(application: DeviceManagementApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providePreferenceService(context: Context): PreferenceService {
        return PreferenceService(context)
    }


    @Provides
    @Singleton
    fun provideFireStoreSettingInstance(): FirebaseFirestoreSettings {
        return FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()
    }


    @Provides
    @Singleton
    fun provideFireStoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }


//
//
//    @Provides
//    @Singleton
//    fun provideFireStoreSettingInstance(): FirebaseFirestoreSettings {
//        return FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()
//    }
//
//
//    @Provides
//    @Singleton
//    fun provideFireStoreInstance(): FirebaseFirestore {
//        return FirebaseFirestore.getInstance()
//    }
//


   

}

