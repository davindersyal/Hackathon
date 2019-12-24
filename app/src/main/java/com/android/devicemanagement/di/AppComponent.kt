package com.android.devicemanagement.di

import com.android.devicemanagement.DeviceManagementApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class
    ]
)
interface AppComponent : AndroidInjector<DeviceManagementApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: DeviceManagementApplication): Builder
        fun build(): AppComponent
    }

      override fun inject(app: DeviceManagementApplication)
}


