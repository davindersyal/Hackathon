package com.android.devicemanagement.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.devicemanagement.R
import com.android.devicemanagement.databinding.ActivityDeviceInfoBinding
import com.android.devicemanagement.databinding.FragmentDashboardBinding
import com.android.devicemanagement.ui.baseclass.BaseDaggerActivity
import com.android.devicemanagement.ui.baseclass.DataBindingActivity
import com.android.devicemanagement.ui.dashboard.DashboardActivity.Companion.DEVICE_NAME
import com.android.devicemanagement.ui.dashboard.DashboardActivity.Companion.IP_ADDRESS
import com.android.devicemanagement.ui.dashboard.adapter.DeviceItemAdapter
import com.android.devicemanagement.ui.dashboard.viewmodel.DeviceItemModel
import com.google.zxing.integration.android.IntentIntegrator
import com.sdi.joyersmajorplatform.common.livedataext.throttleClicks
import kotlinx.android.synthetic.main.activity_device_info.*
import kotlinx.android.synthetic.main.activity_start_up.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.json.JSONException
import org.json.JSONObject

class DeviceInfoActivity : DataBindingActivity<ActivityDeviceInfoBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_device_info

    val viewModel: DeviceItemModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.extras?.getString(DEVICE_NAME)?.let { device_name.setText(it) }
        intent?.extras?.getString(IP_ADDRESS)?.let { ip_address.setText(it) }
        subscribe(submitbtn.throttleClicks()){
          viewModel.setMethod()
        }
    }


    override fun onBindView(binding: ActivityDeviceInfoBinding) {
        binding.vm = viewModel
    }



}
