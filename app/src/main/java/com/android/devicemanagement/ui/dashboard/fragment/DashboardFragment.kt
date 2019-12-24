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


class DashboardFragment : BaseDaggerListFragment<FragmentDashboardBinding>() {

    override val layoutRes: Int get() = R.layout.fragment_dashboard


    val viewModel: DeviceItemModel by viewModels()

    private lateinit var qrScan: IntentIntegrator

    companion object{
        const val GRID_ITEM_WIDTH = 400

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAdapter()
        clickListener()
    }

    private fun clickListener() {
        subscribe(addItem.throttleClicks()){
            qrScan.initiateScan()
        }
    }

    private fun initializeAdapter() {
        qrScan =  IntentIntegrator(requireActivity())
       // initAdapter(DeviceItemAdapter(),deviceList,viewModel.list)//.adapterItemClickHandler()
       // foodList.layoutManager = AutoFitGridLayoutManager(requireContext(),GRID_ITEM_WIDTH)
    }

//    private fun ProductTypeItemAdapter.adapterItemClickHandler(){
//        subscribe(this.itemClicks){
//          Navigator(requireContext()).goToFindFood()
//        }
//
//    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            //if qrcode has nothing in it
            if (result.contents == null) {
                Toast.makeText(requireContext(), "Result Not Found", Toast.LENGTH_LONG).show()
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    val obj = JSONObject(result.contents)
                    //setting values to textviews
                    Log.e("information are", "" + obj.getString("name") + obj.getString("address"))

                } catch (e: JSONException) {
                    e.printStackTrace()
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(requireContext(), result.contents, Toast.LENGTH_LONG).show()
                }


            }

        }

    }


}
