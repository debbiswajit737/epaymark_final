package com.epaymark.epay.ui.fragment.regandkyc



import android.app.DatePickerDialog
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.window.layout.WindowMetricsCalculator
import com.epaymark.epay.R
import com.epaymark.epay.adapter.PhonePadAdapter
import com.epaymark.epay.adapter.StateListAdapter
import com.epaymark.epay.data.model.StateCityModel
import com.epaymark.epay.data.viewMovel.AuthViewModel
import com.epaymark.epay.databinding.FragmentLoginMobileBinding
import com.epaymark.epay.databinding.FragmentRegBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.fragment.CameraDialog
import com.epaymark.epay.utils.*
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.PermissionUtils
import com.epaymark.epay.utils.helpers.PermissionUtils.createAlertDialog
import com.epaymark.epay.utils.`interface`.CallBack
import com.epaymark.epay.utils.`interface`.KeyPadOnClickListner
import com.epaymark.epay.utils.`interface`.PermissionsCallback
import java.net.URLEncoder
import java.util.Calendar


class LoginMobileFragment : BaseFragment() {
    lateinit var binding: FragmentLoginMobileBinding
    var keyPad = ArrayList<Int>()
    private val authViewModel: AuthViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_mobile, container, false)
        binding.viewModel=authViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setKeyPad(binding.recyclePhonePad)
        onViewClick()
    }

    private fun onViewClick() {
        binding.apply {
            btnConfirmLocation.setOnClickListener {
                authViewModel.mobError.value=""
                if (viewModel?.keyPadValue?.value?.length==10){
                    findNavController().navigate(R.id.action_loginMobileFragment_to_otpMobileFragment)
                }
                else{
                    authViewModel.mobError.value="Please enter a valid mobile number."
                }


            }
        }

        }
    fun setKeyPad(PhonePad: RecyclerView) {
        authViewModel.mobError.value=""
        keyPad.clear()
        keyPad.add(1)
        keyPad.add(2)
        keyPad.add(3)
        keyPad.add(4)
        keyPad.add(5)
        keyPad.add(6)
        keyPad.add(7)
        keyPad.add(8)
        keyPad.add(9)
        keyPad.add(10)
        keyPad.add(0)
        keyPad.add(11)
        PhonePad.apply {
            //authViewModel.keyPadValue.value=getString(R.string.mobile_no_hint)
            adapter= PhonePadAdapter(keyPad,object : KeyPadOnClickListner {
                override fun onClick(item: Int) {
                    authViewModel.mobError.value = ""
                    authViewModel.keyPadValue.value?.apply {
                        if (item <= 9) {
                            if (this.length != 10) {
                                authViewModel.keyPadValue.value = "${this}$item"
                            }
                        } else if (item == 10) {
                            //authViewModel.keyPadValue.value =""
                        } else {
                            if (this.isNotEmpty()) {
                                authViewModel.keyPadValue.value = this.substring(0, this.length - 1)
                            }

                        }
                    }


                }

            })
            isNestedScrollingEnabled=false
        }
    }
    }


