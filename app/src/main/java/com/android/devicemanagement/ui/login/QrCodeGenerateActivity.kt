package com.android.devicemanagement.ui.login

import android.graphics.Bitmap
import android.graphics.Point
import android.os.Bundle
import android.view.WindowManager
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.android.devicemanagement.R
import com.android.devicemanagement.databinding.ActivityDeviceInfoBinding
import com.android.devicemanagement.ui.baseclass.DataBindingActivity
import com.android.devicemanagement.ui.login.fragment.SignupFragment
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.qr_code_screen.*
import org.json.JSONObject

class QrCodeGenerateActivity : DataBindingActivity<ActivityDeviceInfoBinding>() {
    override val layoutRes: Int
        get() = R.layout.qr_code_screen


    private var inputValue: String = ""
    lateinit var qrgEncoder: QRGEncoder
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.extras?.getString(SignupFragment.DEVICE_ID)?.let {
            generateCode(generateJsonString(it))
        }

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun generateCode(json: String) {
        inputValue = json
        if (inputValue.length > 0) {
            val manager = getSystemService(WINDOW_SERVICE) as WindowManager
            val display = manager.defaultDisplay
            val point = Point()
            display.getSize(point)
            val width = point.x
            val height = point.y
            var smallerDimension = if (width < height) width else height
            smallerDimension = smallerDimension * 3 / 4

            qrgEncoder = QRGEncoder(
                inputValue, null,
                QRGContents.Type.TEXT,
                smallerDimension
            )
            try {
                bitmap = qrgEncoder.encodeAsBitmap()
                QR_Image.setImageBitmap(bitmap)
            } catch (e: WriterException) {
            }

        } else {
            showMessage("Some thing went wrong")
        }
    }


    private fun generateJsonString(Id: String): String {
        return JSONObject().put("Id", Id).toString()


    }


}
