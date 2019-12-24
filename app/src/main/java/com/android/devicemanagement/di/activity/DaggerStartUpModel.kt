package com.android.devicemanagement.di.activity

import com.android.devicemanagement.ui.login.fragment.ForgotPasswordFragment
import com.android.devicemanagement.ui.login.fragment.LoginFragment
import com.android.devicemanagement.ui.login.fragment.SignupFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DaggerStartUpModel {

    @ContributesAndroidInjector
    abstract fun contributeSignup(): SignupFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeForgotPasswordFragment(): ForgotPasswordFragment

}


