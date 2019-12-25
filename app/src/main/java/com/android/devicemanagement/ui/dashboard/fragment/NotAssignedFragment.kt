package com.android.devicemanagement.ui.dashboard.fragment

import android.os.Bundle
import android.view.View
import com.android.devicemanagement.ui.baseclass.BaseDaggerListFragment
import com.android.devicemanagement.ui.dashboard.adapter.DeviceItemAdapter
import com.android.devicemanagement.ui.dashboard.viewmodel.DeviceItemModel
import androidx.lifecycle.Observer
import com.android.devicemanagement.databinding.FragmentAssigedLayoutBinding
import com.android.devicemanagement.ui.dashboard.AddDeviceActivity.Companion.UN_ASSIGNED
import com.android.devicemanagement.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_assiged_layout.*
import androidx.appcompat.app.AlertDialog
import com.android.devicemanagement.R


class NotAssignedFragment : BaseDaggerListFragment<FragmentAssigedLayoutBinding>() {

    override val layoutRes: Int get() = R.layout.fragment_assiged_layout

    private val viewModel: DeviceItemModel by activityViewModels()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        
        viewModel.deviceListResponse.observe(viewLifecycleOwner, Observer {
            progressBar.visibility =View.GONE
            if(it.isNullOrEmpty()){
               // showMessage("No record found")
            }
            initAdapter(DeviceItemAdapter(), assignedList, it).adapterItemClickHandler()
        })
    }

    override fun onResume() {
        super.onResume()
       // progressBar.visibility =View.VISIBLE
        viewModel.fetchDeviceList(UN_ASSIGNED)
    }

    private fun DeviceItemAdapter.adapterItemClickHandler() {
        subscribe(this.itemClicks) {
            (activity as DashboardActivity).openQrCode(it)
        }

//        subscribe(this.iteDelete){
//            alertDialog(it.id)
//        }


    }



    private fun alertDialog(deviceId: String) {
        val builder1 = AlertDialog.Builder(requireContext())
        builder1.setMessage("Do you want to Delete item ?")
        builder1.setCancelable(true)

        builder1.setPositiveButton("Yes") { dialog, id ->
            viewModel.deleteItem(deviceId){
                if(it){
                    dialog.cancel()
                    viewModel.fetchDeviceList(UN_ASSIGNED)
                } else{
                    showMessage("Unable to delete this item,Please try again")
                    dialog.cancel()
                }
            }


        }

        builder1.setNegativeButton(
            "No", { dialog, id -> dialog.cancel() })

        val alert11 = builder1.create()
        alert11.show()


    }


   

}
