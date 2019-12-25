package com.android.devicemanagement.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.android.devicemanagement.R
import com.android.devicemanagement.data.api.models.DeviceListResponse
import com.android.devicemanagement.databinding.FragmentDashboardBinding
import com.android.devicemanagement.ui.baseclass.DataBindingActivity
import com.android.devicemanagement.ui.dashboard.adapter.DeviceItemAdapter
import com.android.devicemanagement.ui.dashboard.adapter.ViewPagerAdapter
import com.android.devicemanagement.ui.dashboard.viewmodel.DeviceItemModel
import com.google.android.material.tabs.TabLayout
import com.google.zxing.integration.android.IntentIntegrator
import com.sdi.joyersmajorplatform.common.livedataext.throttleClicks
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.json.JSONException
import org.json.JSONObject
import com.android.devicemanagement.ui.dashboard.fragment.AssignedFragment
import com.android.devicemanagement.ui.dashboard.fragment.NotAssignedFragment


class DashboardActivity : DataBindingActivity<FragmentDashboardBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_dashboard

    private val viewModel: DeviceItemModel by viewModels()

    private lateinit var qrScan: IntentIntegrator

   lateinit var deviceListResponse :DeviceListResponse

    companion object {
        const val DEVICE_NAME = "device_name"
        const val OS = "operating_system"
        const val DEVICE_ID="device_id"
        const val DEVICE_STATUS ="device_status"
        const val USER_NAME ="user_name"
        const val USER_SEAT_NUMBER ="seat_number"
        const val USER_ID ="user_Id"


        const val ASSIGNED = "Assigned"
        const val NOT_ASSIGNED = "Not Assigned"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()

        initializeAdapter()
        clickListener()


    }

    private fun initializeView() {
        tab_layout.addTab(tab_layout.newTab().setText(NOT_ASSIGNED))
        tab_layout.addTab(tab_layout.newTab().setText(ASSIGNED))

        val pagerAdapter = ViewPagerAdapter(supportFragmentManager, tab_layout.tabCount)
        view_pager.adapter = pagerAdapter
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
              
                view_pager.currentItem = tab.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {



            }

            override fun onTabReselected(tab: TabLayout.Tab) {


            }
        })
    }


    private fun clickListener() {
        subscribe(addItem.throttleClicks()) {
            val intent = Intent(this, AddDeviceActivity::class.java)
            startActivity(intent)

        }
    }

    private fun initializeAdapter() {
        qrScan = IntentIntegrator(this)
      

    }

    private fun DeviceItemAdapter.adapterItemClickHandler() {
        subscribe(this.itemClicks) {
            //   viewModel.deleteItem(it.id)
            viewModel.updateItem(it.id)
        }
    }

     fun openQrCode(it: DeviceListResponse) {
         deviceListResponse = it


        qrScan.initiateScan()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            //if qrcode has nothing in it
            if (result.contents == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show()
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    val obj = JSONObject(result.contents)
                    deviceListResponse.let { deviceId->
                        if(deviceId.id.equals(obj.getString("Id"),ignoreCase = true)){
                            val intent = Intent(this, DeviceInfoActivity::class.java)
                            deviceId.device_name?.let {
                                intent.putExtra(DEVICE_NAME, it)
                            }
                            deviceId.device_status?.let {
                                intent.putExtra(DEVICE_STATUS, it)
                            }
                            deviceId.os?.let {
                                intent.putExtra(OS, it)
                            }
                            intent.putExtra(DEVICE_ID,deviceId.id)
                            startActivity(intent)
                        } else{
                            Toast.makeText(this,"Unable to find device Id",Toast.LENGTH_LONG).show()
                        }
                    }
                    
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
                }


            }

        }

    }

    

}
