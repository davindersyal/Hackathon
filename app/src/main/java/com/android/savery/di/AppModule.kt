package com.android.savery.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.android.savery.SaveryApplication
import com.android.savery.data.service.PreferenceService
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [UiModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(application: SaveryApplication): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideContext(application: SaveryApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providePreferenceService(context: Context): PreferenceService {
        return PreferenceService(context)
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

