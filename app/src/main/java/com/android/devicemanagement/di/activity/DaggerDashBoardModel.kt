package com.android.devicemanagement.di.activity

import com.android.devicemanagement.ui.dashboard.fragment.DashboardFragment
import com.android.devicemanagement.ui.login.fragment.ForgotPasswordFragment
import com.android.devicemanagement.ui.login.fragment.LoginFragment
import com.android.devicemanagement.ui.login.fragment.SignupFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DaggerDashBoardModel {

    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment



}


