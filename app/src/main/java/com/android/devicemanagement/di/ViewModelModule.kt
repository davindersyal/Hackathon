package com.android.devicemanagement.di

import androidx.lifecycle.ViewModel
import com.android.devicemanagement.ui.dashboard.viewmodel.DeviceItemModel
import com.android.devicemanagement.ui.login.viewmodel.LoginFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentViewModel::class)
    abstract fun bindLoginFragmentViewModel(
        viewModel: LoginFragmentViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeviceItemModel::class)
    abstract fun bindDeviceItemModel(
        viewModel: DeviceItemModel
    ): ViewModel



//
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(SignupFragmentViewModel::class)
//    abstract fun bindSignupFragmentViewModel(
//        viewModel: SignupFragmentViewModel
//    ): ViewModel
//
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(IdenticalJoyersViewModel::class)
//    abstract fun bindIdenticalJoyersViewModel(
//        viewModel: IdenticalJoyersViewModel
//    ): ViewModel
//
//
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(SignUpViewModel::class)
//    abstract fun bindSignUpViewModel(
//        viewModel: SignUpViewModel
//    ): ViewModel
//
//
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(SignupFragmentModel1::class)
//    abstract fun bindSignupFragmentModel1(
//        viewModel: SignupFragmentModel1
//    ): ViewModel
//
//
//
//
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ProfileFragmentViewModel::class)
//    abstract fun bindProfileFragmentViewModel(
//        viewModel: ProfileFragmentViewModel
//    ): ViewModel
//
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ForgotPasswordViewModel::class)
//    abstract fun bindForgotPasswordViewModel(
//        viewModel: ForgotPasswordViewModel
//    ): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(PostJoyFragmentViewModel::class)
//    abstract fun bindPostJoyFragmentViewModel(
//        viewModel: PostJoyFragmentViewModel
//    ): ViewModel





}
