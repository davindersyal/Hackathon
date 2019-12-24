package com.android.devicemanagement.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.android.devicemanagement.R
import com.android.devicemanagement.databinding.FragmentDashboardBinding
import com.android.devicemanagement.ui.baseclass.DataBindingActivity
import com.android.devicemanagement.ui.dashboard.adapter.DeviceItemAdapter
import com.android.devicemanagement.ui.dashboard.viewmodel.DeviceItemModel
import com.google.zxing.integration.android.IntentIntegrator
import com.sdi.joyersmajorplatform.common.livedataext.throttleClicks
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.json.JSONException
import org.json.JSONObject

class DashboardActivity : DataBindingActivity<FragmentDashboardBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_dashboard

    private val viewModel: DeviceItemModel by viewModels()

    private lateinit var qrScan: IntentIntegrator

    companion object {
      const val   DEVICE_NAME ="device_name"
        const val IP_ADDRESS ="IpAddress"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeAdapter()
        clickListener()

    }


    private fun clickListener() {
        subscribe(addItem.throttleClicks()){
            qrScan.initiateScan();
            //viewModel.setMethod()
           // viewModel.fetchLiveData()

        }
    }

    private fun initializeAdapter() {
        qrScan =  IntentIntegrator(this)
        viewModel.deviceListResponse.observe(this, Observer {
            initAdapter(DeviceItemAdapter(),deviceList,it).adapterItemClickHandler()
        })

         //    initAdapter(DeviceItemAdapter(),deviceList,viewModel.deviceListResponse)//.adapterItemClickHandler()
         //foodList.layoutManager = AutoFitGridLayoutManager(requireContext(),GRID_ITEM_WIDTH)
    }

    private fun DeviceItemAdapter.adapterItemClickHandler(){
        subscribe(this.itemClicks){
        //   viewModel.deleteItem(it.id)
           viewModel.updateItem(it.id)

        }

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


                    val intent =  Intent(this,DeviceInfoActivity::class.java)
                    intent.putExtra(DEVICE_NAME,obj.getString(DEVICE_NAME))
                    intent.putExtra(IP_ADDRESS,obj.getString(IP_ADDRESS))
                    startActivity(intent)


                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
                }


            }

        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchLiveData()
    }

}
