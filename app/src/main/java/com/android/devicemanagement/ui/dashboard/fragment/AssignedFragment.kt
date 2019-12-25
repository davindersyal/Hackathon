package com.android.devicemanagement.ui.dashboard.fragment

import android.os.Bundle
import android.view.View
import com.android.devicemanagement.R
import com.android.devicemanagement.ui.baseclass.BaseDaggerListFragment
import com.android.devicemanagement.ui.dashboard.adapter.DeviceItemAdapter
import com.android.devicemanagement.ui.dashboard.viewmodel.DeviceItemModel
import android.content.Intent
import androidx.lifecycle.Observer
import com.android.devicemanagement.databinding.FragmentAssigedLayoutBinding
import com.android.devicemanagement.ui.dashboard.AddDeviceActivity.Companion.ASSIGNED
import com.android.devicemanagement.ui.dashboard.DashboardActivity
import com.android.devicemanagement.ui.dashboard.DeviceInfoActivity
import com.sdi.joyersmajorplatform.common.progressDialog
import kotlinx.android.synthetic.main.fragment_assiged_layout.*


class AssignedFragment : BaseDaggerListFragment<FragmentAssigedLayoutBinding>() {

    override val layoutRes: Int get() = R.layout.fragment_assiged_layout

    private val viewModel: DeviceItemModel by viewModels()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = progressDialog(R.string.fetching)
        viewModel.deviceListResponse.observe(viewLifecycleOwner, Observer {
            progressBar.visibility =View.GONE
            if(it.isNullOrEmpty()){
             //   showMessage("No record found")
            }
            initAdapter(DeviceItemAdapter(), assignedList, it).adapterItemClickHandler()
        })
        
    }


    private fun DeviceItemAdapter.adapterItemClickHandler() {
        subscribe(this.itemClicks) { deviceListResponse->
            dialog?.show()
            val intent = Intent(requireContext(), DeviceInfoActivity::class.java)
            deviceListResponse.device_status?.let {

                viewModel.fetchUserList(deviceListResponse.device_status,deviceListResponse.id) { info->
                    info?.let{userListResponse->
                        dialog?.dismiss()
                        deviceListResponse.device_name?.let { intent.putExtra(DashboardActivity.DEVICE_NAME, it) }
                        intent.putExtra(DashboardActivity.USER_NAME,userListResponse.user_name)
                        intent.putExtra(DashboardActivity.USER_SEAT_NUMBER,userListResponse.seat_number)
                        intent.putExtra(DashboardActivity.DEVICE_STATUS, it)
                        intent.putExtra(DashboardActivity.USER_ID, userListResponse.id)
                        deviceListResponse.os?.let { intent.putExtra(DashboardActivity.OS, it) }
                        intent.putExtra(DashboardActivity.DEVICE_ID,deviceListResponse.id)
                        startActivity(intent)
                    }?:run{
                        dialog?.dismiss()
                        showMessage("Something went wrong, Please try again")
                    }




                }
            }



        }

    }



    override fun onResume() {
        super.onResume()
      //  progressBar.visibility =View.VISIBLE
        viewModel.fetchDeviceList(ASSIGNED)

        
    }
  




}
