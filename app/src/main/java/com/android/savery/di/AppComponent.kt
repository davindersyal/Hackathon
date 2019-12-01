package com.android.savery.di

import com.android.savery.SaveryApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
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
interface AppComponent : AndroidInjector<SaveryApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: SaveryApplication): Builder
        fun build(): AppComponent
    }

      override fun inject(app: SaveryApplication)
}


