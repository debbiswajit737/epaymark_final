package com.epaymark.epay.ui.fragment.regandkyc



import android.animation.Animator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.R
import com.epaymark.epay.adapter.PhonePadAdapter
import com.epaymark.epay.data.viewMovel.AuthViewModel
import com.epaymark.epay.databinding.FragmentOtpMobileBinding
import com.epaymark.epay.network.ResponseState
import com.epaymark.epay.network.RetrofitHelper.handleApiError
import com.epaymark.epay.ui.activity.AuthenticationActivity
import com.epaymark.epay.ui.activity.DashboardActivity
import com.epaymark.epay.ui.activity.RegActivity
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.popup.LoadingPopup
import com.epaymark.epay.utils.common.MethodClass
import com.epaymark.epay.utils.common.MethodClass.getCurrentTimestamp
import com.epaymark.epay.utils.common.MethodClass.getLocalIPAddress
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.`interface`.KeyPadOnClickListner
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import kotlinx.coroutines.launch


class OtpMobileFragment : BaseFragment() {
    lateinit var binding: FragmentOtpMobileBinding
    var keyPad = ArrayList<Int>()
    private val authViewModel: AuthViewModel by activityViewModels()
    var isForgotPinPage=false
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    var loadingPopup: LoadingPopup? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp_mobile, container, false)
        binding.viewModel=authViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setKeyPad(binding.recyclePhonePad2)
        onViewClick()
        observer()
        isForgotPinPage= arguments?.getBoolean("isForgotPin") == true
    }

    private fun observer() {
        authViewModel?.otpResponse?.observe(viewLifecycleOwner){
            when (it) {
                is ResponseState.Loading -> {
                    loadingPopup?.show()
                }

                is ResponseState.Success -> {
                    Toast.makeText(requireContext(), ""+it.data?.Description, Toast.LENGTH_SHORT).show()
                    if (it.data?.step==1){
                       // if(authViewModel.otp.value=="123456"){
                                    //binding.lottieTickAnim.visibility=View.VISIBLE
                                    binding.lottieConfettiAnim.visibility=View.VISIBLE
                                    if (!isForgotPinPage) {
                                        it.data?.step?.let {
                                            setdata2(it)
                                        }

                                    }
                                    else{
                                        activity?.let {act->
                                            val intent = Intent(act, DashboardActivity::class.java)
                                            startActivity(intent)
                                            act.finish()
                                        }
                                    }
                                    //findNavController().navigate(R.id.action_otpFragment_to_congratulationFragment)
                                    // Toast.makeText(requireContext(), "match", Toast.LENGTH_SHORT).show()
                              //  }
                    }
                }

                is ResponseState.Error -> {
                    //   loadingPopup?.dismiss()
                    handleApiError(it.isNetworkError, it.errorCode, it.errorMessage)
                }
            }
        }
    }

    fun init(){
       activity?.let {  mFusedLocationClient = LocationServices.getFusedLocationProviderClient(it)}
    }
    private fun onViewClick() {


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
                    authViewModel.otp.value?.apply {
                        if (item<=9 ) {
                            if (this.length!=6) {
                                authViewModel.otp.value = "${this}${item}"
                                val (isLogin, loginResponse) =sharedPreff.getLoginData()
                                loginResponse?.let {loginData->
                                    loginResponse

                                    val data = mapOf(
                                        "otp" to authViewModel.otp.value,
                                        "userid" to loginData.userid,

                                        "deviceid" to MethodClass.deviceUid(binding.root.context),
                                        "ipaddress" to getLocalIPAddress(),
                                        "location" to "123",
                                        "referenceid" to "123",
                                         "Timestamp" to getCurrentTimestamp()
                                    )
                                    /*"referenceid" to loginData.,*/
                                    val gson= Gson()
                                    var jsonString = gson.toJson(data)

                                    if (this.length==5) {
                                        loginData.AuthToken?.let {
                                        authViewModel?.sendOtp(it,jsonString.encrypt()) }
                                    }

                                }
                                // binding.firstPinView.text=this

                                /*if(authViewModel.otp.value=="123456"){
                                    //binding.lottieTickAnim.visibility=View.VISIBLE
                                    binding.lottieConfettiAnim.visibility=View.VISIBLE
                                    if (!isForgotPinPage) {
                                        setdata2()
                                    }
                                    else{
                                        activity?.let {act->
                                            val intent = Intent(act, DashboardActivity::class.java)
                                            startActivity(intent)
                                            act.finish()
                                        }
                                    }
                                    //findNavController().navigate(R.id.action_otpFragment_to_congratulationFragment)
                                    // Toast.makeText(requireContext(), "match", Toast.LENGTH_SHORT).show()
                                }*/

                                //binding.firstPinView.setText(authViewModel.otp.value)
                            }
                        }
                        else if(item==10){
                            //authViewModel.keyPadValue.value =""
                        }
                        else {
                            if (this.isNotEmpty()) {
                                authViewModel.otp.value = this.toString().substring(0, this.length - 1)
                                //binding.firstPinView.setText(authViewModel.otp.value)

                            }

                        }
                    }
                }

            })
            isNestedScrollingEnabled=false
        }
    }

    private fun setdata2(step: Int) {
        //binding.lottieTickAnim.setAnimationFromUrl(Constants.LOTTIE_TICK_LINK)
        //binding.lottieTickAnim.playAnimation()
        binding.lottieConfettiAnim.setAnimationFromUrl(Constants.LOTTIE_CONFETTIE_LINK)
        //        lottie_anim.setSpeed(0.7f);
        binding.lottieConfettiAnim.playAnimation()
        binding.lottieConfettiAnim.addAnimatorListener(
            object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    lifecycleScope.launch {
                        //delay(500L)
                        (activity as? RegActivity)?.let {
                            val intent=Intent(requireActivity(), AuthenticationActivity::class.java)
                            intent.putExtra("stape",step.toString())
                            startActivity(intent)
                            it.finish()
                        }
                    }
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
    }



    }


