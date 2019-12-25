package com.android.devicemanagement.ui.dashboard.fragment

import android.os.Bundle
import android.view.View
import com.android.devicemanagement.R
import com.android.devicemanagement.ui.baseclass.BaseDaggerListFragment
import com.android.devicemanagement.ui.dashboard.adapter.DeviceItemAdapter
import com.android.devicemanagement.ui.dashboard.viewmodel.DeviceItemModel
import androidx.lifecycle.Observer
import com.android.devicemanagement.databinding.FragmentAssigedLayoutBinding
import com.android.devicemanagement.ui.dashboard.AddDeviceActivity.Companion.UN_ASSIGNED
import com.android.devicemanagement.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_assiged_layout.*


class NotAssignedFragment : BaseDaggerListFragment<FragmentAssigedLayoutBinding>() {

    override val layoutRes: Int get() = R.layout.fragment_assiged_layout

    private val viewModel: DeviceItemModel by activityViewModels()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        
        viewModel.deviceListResponse.observe(viewLifecycleOwner, Observer {
            initAdapter(DeviceItemAdapter(), assignedList, it).adapterItemClickHandler()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchDeviceList(UN_ASSIGNED)
    }

    private fun DeviceItemAdapter.adapterItemClickHandler() {
        subscribe(this.itemClicks) {
            (activity as DashboardActivity).openQrCode(it)
        }

    }


   

}
