package com.android.devicemanagement.ui.dashboard.fragment

import android.os.Bundle
import android.view.View
import com.android.devicemanagement.R
import com.android.devicemanagement.databinding.FragmentDashboardBinding
import com.android.devicemanagement.ui.baseclass.BaseDaggerListFragment
import com.android.devicemanagement.ui.dashboard.adapter.DeviceItemAdapter
import com.android.devicemanagement.ui.dashboard.viewmodel.DeviceItemModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import com.google.zxing.integration.android.IntentIntegrator
import com.sdi.joyersmajorplatform.common.livedataext.throttleClicks
import android.widget.Toast
import org.json.JSONException
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import org.json.JSONObject
import com.google.zxing.integration.android.IntentResult
import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import com.android.devicemanagement.databinding.FragmentAssigedLayoutBinding
import com.android.devicemanagement.ui.dashboard.AddDeviceActivity.Companion.ASSIGNED
import com.android.devicemanagement.ui.dashboard.DashboardActivity
import com.android.devicemanagement.ui.dashboard.DeviceInfoActivity
import kotlinx.android.synthetic.main.fragment_assiged_layout.*


class AssignedFragment : BaseDaggerListFragment<FragmentAssigedLayoutBinding>() {

    override val layoutRes: Int get() = R.layout.fragment_assiged_layout

    private val viewModel: DeviceItemModel by viewModels()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.deviceListResponse.observe(viewLifecycleOwner, Observer {
            initAdapter(DeviceItemAdapter(), assignedList, it).adapterItemClickHandler()
        })


        
        
    }


    private fun DeviceItemAdapter.adapterItemClickHandler() {
        subscribe(this.itemClicks) { deviceListResponse->
            val intent = Intent(requireContext(), DeviceInfoActivity::class.java)
            deviceListResponse.device_status?.let {

                
                viewModel.fetchUserList(deviceListResponse.device_status,deviceListResponse.id) { userListResponse->
                    deviceListResponse.device_name?.let { intent.putExtra(DashboardActivity.DEVICE_NAME, it) }
                    intent.putExtra(DashboardActivity.USER_NAME,userListResponse.user_name)
                    intent.putExtra(DashboardActivity.USER_SEAT_NUMBER,userListResponse.seat_number)
                    intent.putExtra(DashboardActivity.DEVICE_STATUS, it)
                    intent.putExtra(DashboardActivity.USER_ID, userListResponse.id)
                    deviceListResponse.os?.let { intent.putExtra(DashboardActivity.OS, it) }
                    intent.putExtra(DashboardActivity.DEVICE_ID,deviceListResponse.id)
                    startActivity(intent)
                }
            }



        }

    }



    override fun onResume() {
        super.onResume()
        viewModel.fetchDeviceList(ASSIGNED)

        
    }
  




}
