package com.android.savery.di

import androidx.lifecycle.ViewModelProvider
import com.android.savery.di.activity.DaggerStartUpModel
import com.android.savery.ui.login.StartUpActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class UiModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @ContributesAndroidInjector(modules = [DaggerStartUpModel::class])
        abstract fun contributeSignUpActivity(): StartUpActivity


//    @ContributesAndroidInjector
//    abstract fun contributeStartUpActivity(): StartUpActivity

//    @ContributesAndroidInjector
//    abstract fun contributeSignup(): SignupFragment
//
//    @ContributesAndroidInjector
//    abstract fun contributeLoginFragment(): LoginFragment
//
//    @ContributesAndroidInjector
//    abstract fun contributeForgotPasswordFragment(): ForgotPasswordFragment


//    @ContributesAndroidInjector(modules = [SignUpActivityModel::class])
//    abstract fun contributeSignUpActivity(): SignUpActivity
//
//    @ContributesAndroidInjector(modules=[DashBoardActivityModel::class])
//    abstract fun contributeDashboardActivity(): DashboardActivity
//
//
//    @ContributesAndroidInjector
//    abstract fun contributeSignupFragment(): SignupFragment




//
//    @ContributesAndroidInjector(modules = [DashboardActivityModel::class])
//    abstract fun contributeDashBoardActivity(): DashboardActivity
//
//
//    @ContributesAndroidInjector()
//    abstract fun contributeHorseEditActivity(): HorseEditActivity
//
//
//    @ContributesAndroidInjector(modules = [StableActivityModel::class])
//    abstract fun contributeStableActivity(): AddStables
//
//    @ContributesAndroidInjector()
//    abstract fun contributeNewHorseActivity(): NewHorseActivity
//
//
//    @ContributesAndroidInjector()
//    abstract fun contributeHorseDescriptionSceen(): HorseDescriptionSceen


}
