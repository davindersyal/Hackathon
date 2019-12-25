package com.android.devicemanagement.ui.login

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Bundle
import android.view.WindowManager
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AlertDialog
import com.android.devicemanagement.R
import com.android.devicemanagement.data.service.PreferenceService
import com.android.devicemanagement.databinding.ActivityDeviceInfoBinding
import com.android.devicemanagement.ui.baseclass.DataBindingActivity
import com.android.devicemanagement.ui.dashboard.AddDeviceActivity
import com.android.devicemanagement.ui.login.fragment.SignupFragment
import com.android.devicemanagement.ui.login.viewmodel.SignupFragmentViewModel
import com.google.zxing.WriterException
import com.sdi.joyersmajorplatform.common.livedataext.throttleClicks
import com.sdi.joyersmajorplatform.common.progressDialog
import kotlinx.android.synthetic.main.qr_code_screen.*
import org.json.JSONObject

class QrCodeGenerateActivity : DataBindingActivity<ActivityDeviceInfoBinding>() {
    override val layoutRes: Int
        get() = R.layout.qr_code_screen

    lateinit var prefference: PreferenceService

    private var inputValue: String = ""
    lateinit var qrgEncoder: QRGEncoder
    lateinit var bitmap: Bitmap

    val viewModel: SignupFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = progressDialog(R.string.update_status)
        prefference = PreferenceService(this)
        intent.extras?.getString(SignupFragment.DEVICE_ID)?.let {
            generateCode(generateJsonString(it))
        }

        toolbar.setNavigationOnClickListener {
            finish()
        }
        subscribe(logout.throttleClicks()) {
            alertDialog()
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

    private fun alertDialog() {
        val builder1 = AlertDialog.Builder(this)
        builder1.setMessage("Do you want to Logout ?")
        builder1.setCancelable(true)

        builder1.setPositiveButton("Yes") { dialog1, id ->
            dialog?.show()
            prefference.getString(R.string.deviceId)?.let {
                viewModel.fetchLiveData(it) { verificationCode, status ->
                    if (status) {
                        viewModel.updateverifiedDeviceUser(verificationCode) {
                            dialog?.dismiss()
                            prefference.clearPreference()
                            dialog1.cancel()

                            val intent = Intent(this, StartUpActivity::class.java)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                        }
                    } else {
                        showMessage("Something went wrong, Please try again")
                        dialog?.dismiss()
                    }
                }
            }
        }

        builder1.setNegativeButton(
            "No", { dialog, id -> dialog.cancel() })

        val alert11 = builder1.create()
        alert11.show()


    }


}
