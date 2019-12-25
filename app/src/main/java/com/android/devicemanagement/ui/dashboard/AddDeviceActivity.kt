package com.android.devicemanagement.ui.dashboard

import android.os.Bundle
import android.widget.Toast
import com.android.devicemanagement.R
import com.android.devicemanagement.databinding.ActivityAddDeviceInfoBinding
import com.android.devicemanagement.ui.baseclass.DataBindingActivity
import com.android.devicemanagement.ui.dashboard.viewmodel.DeviceItemModel
import com.sdi.joyersmajorplatform.common.livedataext.throttleClicks
import com.sdi.joyersmajorplatform.common.progressDialog
import kotlinx.android.synthetic.main.activity_add_device_info.*
import kotlinx.android.synthetic.main.activity_device_info.submitbtn

class AddDeviceActivity : DataBindingActivity<ActivityAddDeviceInfoBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_add_device_info

    private val viewModel: DeviceItemModel by viewModels()

    companion object {
        const val ASSIGNED = "assigned"
        const val UN_ASSIGNED = "unassigned"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = progressDialog(R.string.saving)
        addviceToolbar.setNavigationOnClickListener {
            finish()
        }

        subscribe(submitbtn.throttleClicks()) {
            dialog?.show()
            viewModel.deviceListMethod {
                if (it) {
                    dialog?.dismiss()
                    finish()
                } else {
                    Toast.makeText(this, "Unable to save. Please try again", Toast.LENGTH_LONG).show()
                    dialog?.dismiss()

                }
            }
        }
    }


    override fun onBindView(binding: ActivityAddDeviceInfoBinding) {
        binding.vm = viewModel
    }


}
