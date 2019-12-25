package com.android.devicemanagement.di.activity

import com.android.devicemanagement.ui.dashboard.fragment.AssignedFragment
import com.android.devicemanagement.ui.dashboard.fragment.NotAssignedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DaggerDashBoardModel {

    @ContributesAndroidInjector
    abstract fun contributeNotAssignedFragment(): NotAssignedFragment

    @ContributesAndroidInjector
    abstract fun contributeAssignedFragment(): AssignedFragment



}


