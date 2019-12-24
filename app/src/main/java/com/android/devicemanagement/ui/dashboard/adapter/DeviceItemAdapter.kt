package com.android.devicemanagement.ui.dashboard.adapter

import androidx.recyclerview.widget.DiffUtil
import com.android.devicemanagement.R
import com.android.devicemanagement.data.api.models.DeviceListResponse
import com.android.devicemanagement.databinding.ItemDeviceInfoBinding
import com.sdi.joyersmajorplatform.common.livedataext.throttle
import com.sdi.joyersmajorplatform.common.livedataext.throttleClicks
import com.sdi.joyersmajorplatform.uiview.recyclerview.DataBoundAdapterClass
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class DeviceItemAdapter : DataBoundAdapterClass<DeviceListResponse,ItemDeviceInfoBinding>(Diff) {
    override val defaultLayoutRes: Int
        get() = R.layout.item_device_info

    private var itemClickEmitter: ObservableEmitter<DeviceListResponse>? = null
    val itemClicks: Observable<DeviceListResponse> = Observable.create<DeviceListResponse> {
        itemClickEmitter = it
    }.throttle()

    override fun bind(bind: ItemDeviceInfoBinding, itemType: DeviceListResponse?, position: Int) {
        itemType?.let { deviceListResponse->
           bind.textView.text = deviceListResponse.device_name
            bind.rootView.throttleClicks().subscribe {
                itemClickEmitter?.onNext(deviceListResponse)
            }
        }


    }

    object Diff : DiffUtil.ItemCallback<DeviceListResponse>() {
        override fun areItemsTheSame(oldItem: DeviceListResponse, newItem: DeviceListResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DeviceListResponse, newItem: DeviceListResponse): Boolean {
            return oldItem == newItem
        }
    }
}