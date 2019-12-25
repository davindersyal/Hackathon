package com.android.devicemanagement.ui.login.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.android.devicemanagement.R
import com.android.devicemanagement.databinding.FragmentSignupBinding
import com.android.devicemanagement.ui.baseclass.BaseDaggerFragment
import com.android.devicemanagement.ui.login.QrCodeGenerateActivity
import com.android.devicemanagement.ui.login.viewmodel.SignupFragmentViewModel
import com.sdi.joyersmajorplatform.common.livedataext.throttleClicks
import kotlinx.android.synthetic.main.fragment_signup.*
import java.util.*
import com.jaredrummler.android.device.DeviceName
import com.sdi.joyersmajorplatform.common.progressDialog


class SignupFragment : BaseDaggerFragment<FragmentSignupBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_signup

    companion object {
        const val DEVICE_ID ="deviceId"
    }


    val viewModel: SignupFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.udid = UUID.randomUUID().toString()
        dialog =progressDialog(R.string.verifing_code)

        subscribe(cancelView.throttleClicks()) {
            val action = SignupFragmentDirections.actionSignupFragmentToLoginFragment()
            findNavController().navigate(action)

        }
        subscribe(verifybtn.throttleClicks()) {
            dialog?.show()
            viewModel.fetchCodeList(textInputEditText.text.toString()) { message, verificaiton, id ->
                if (verificaiton) {
                    dialog?.setTitle(R.string.saving_device_detail)
                    viewModel.deviceListMethod {
                        if (it) {
                            dialog?.setTitle(R.string.update_status)
                            viewModel.updateVerificationItem(id) { message, verificaiton, id ->
                                if (it) {
                                    dialog?.dismiss()
                                   val intent = Intent(requireContext(), QrCodeGenerateActivity::class.java)
                                    intent.putExtra(DEVICE_ID,viewModel.deviceId)
                                    startActivity(intent)
                                }else {
                                    dialog?.dismiss()
                                    showMessage(message)
                                }
                            }
                        } else {
                            dialog?.dismiss()
                            showMessage("Something went wrong, Try again with new code")
                        }

                    }
                } else {
                    dialog?.dismiss()
                    showMessage(message)
                }
            }
        }


        DeviceName.with(context).request { info, error ->
            viewModel.deviceName = info.name
            viewModel.udid = info.codename

        }

    }
}
