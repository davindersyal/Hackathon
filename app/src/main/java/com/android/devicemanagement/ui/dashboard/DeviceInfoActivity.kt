package com.android.devicemanagement.ui.dashboard

import android.os.Bundle
import com.android.devicemanagement.R
import com.android.devicemanagement.databinding.ActivityDeviceInfoBinding
import com.android.devicemanagement.ui.baseclass.DataBindingActivity
import com.android.devicemanagement.ui.dashboard.AddDeviceActivity.Companion.UN_ASSIGNED
import com.android.devicemanagement.ui.dashboard.DashboardActivity.Companion.DEVICE_ID
import com.android.devicemanagement.ui.dashboard.DashboardActivity.Companion.DEVICE_NAME
import com.android.devicemanagement.ui.dashboard.DashboardActivity.Companion.DEVICE_STATUS
import com.android.devicemanagement.ui.dashboard.DashboardActivity.Companion.OS
import com.android.devicemanagement.ui.dashboard.DashboardActivity.Companion.USER_ID
import com.android.devicemanagement.ui.dashboard.DashboardActivity.Companion.USER_NAME
import com.android.devicemanagement.ui.dashboard.DashboardActivity.Companion.USER_SEAT_NUMBER
import com.android.devicemanagement.ui.dashboard.viewmodel.DeviceItemModel
import com.android.devicemanagement.ui.dashboard.viewmodel.UserInfoViewModel
import com.sdi.joyersmajorplatform.common.livedataext.throttleClicks
import kotlinx.android.synthetic.main.activity_device_info.*

class DeviceInfoActivity : DataBindingActivity<ActivityDeviceInfoBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_device_info

    private val viewModel: UserInfoViewModel by viewModels()

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.extras?.getString(DEVICE_NAME)?.let { device_name.setText(it) }
        intent?.extras?.getString(OS)?.let { ip_address.setText(it) }
        intent?.extras?.getString(DEVICE_ID)?.let { viewModel.deviceId =it }
        intent?.extras?.getString(DEVICE_STATUS)?.let { viewModel.deviceStatus = it
            if(it.equals(AddDeviceActivity.ASSIGNED,ignoreCase = true)){ submitbtn.setText("UnAssign") } }
        intent?.extras?.getString(USER_NAME)?.let { username.setText(it) }
        intent?.extras?.getString(USER_SEAT_NUMBER)?.let { seat_number.setText(it)}
        intent?.extras?.getString(USER_ID)?.let { viewModel.userId =it}





        subscribe(submitbtn.throttleClicks()){
            if(viewModel.deviceStatus.equals(AddDeviceActivity.ASSIGNED,ignoreCase = true)){
                 viewModel.updateUserList()
            } else{
                viewModel.assignUserList{
                    if(it){
                    }
                }
            }
            
        }


    }


    override fun onBindView(binding: ActivityDeviceInfoBinding) {
        binding.vm = viewModel
    }



}
