package com.epaymark.epay.ui.fragment


import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.adapter.PhonePadAdapter
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentCreditCardPaymentBinding
import com.epaymark.epay.databinding.FragmentEpotlyBinding
import com.epaymark.epay.databinding.FragmentMobileRechargeBinding
import com.epaymark.epay.databinding.FragmentTransactionOtpBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.Constants.isDthOperator
import com.epaymark.epay.utils.`interface`.CallBack
import com.epaymark.epay.utils.`interface`.KeyPadOnClickListner
import java.util.concurrent.TimeUnit

class TransactionOtpFragment : BaseFragment() {
    lateinit var binding: FragmentTransactionOtpBinding
    private val viewModel: MyViewModel by activityViewModels()
    var keyPad = ArrayList<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_otp, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
        onViewClick()
    }

    private fun onViewClick() {
        binding.apply {

            imgBack.back()
            tdResendOtp.setOnClickListener {
                    if (tdResendOtp.text.toString()==getString(R.string.resend_otp_title)){
                        //otp api call
                        cownDown()
                    }
            }



            btnSubmit.setOnClickListener{
                activity?.let {act->

                }

            }

        }



    }

    fun initView() {
        binding.apply {
            viewModel?.apply {
                otpMobile.value=credit_mobile.value
            }

        }


            cownDown()
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
            binding.recyclePhonePad.apply {
                //authViewModel.keyPadValue.value=getString(R.string.mobile_no_hint)
                adapter= PhonePadAdapter(keyPad,object : KeyPadOnClickListner {
                    override fun onClick(item: Int) {

                        viewModel?.otp?.value?.apply {
                            if (item<=9 ) {
                                if (this.length!=6) {

                                    // binding.firstPinView.text=this
                                    viewModel.otp.value = "${this}${item}"
                                    if(viewModel.otp.value=="123456"){

                                         Toast.makeText(requireContext(), "match", Toast.LENGTH_SHORT).show()
                                    }

                                    //binding.firstPinView.setText(authViewModel.otp.value)
                                }
                            }
                            else if(item==10){
                                //authViewModel.keyPadValue.value =""
                            }
                            else {
                                if (this.isNotEmpty()) {
                                    viewModel.otp.value = this.toString().substring(0, this.length - 1)
                                    //binding.firstPinView.setText(authViewModel.otp.value)

                                }

                            }
                        }

                    }

                })
                isNestedScrollingEnabled=false
            }



    }

    fun setObserver() {

    }

    fun cownDown() {


        val totalTimeInMillis: Long = TimeUnit.SECONDS.toMillis(120) // 30 seconds countdown

        var countDownTimer= object : CountDownTimer(totalTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                //authViewModel.timingValue.value = getString(R.string.resend_otp,secondsRemaining)/*"Resend OTP after <font color='#B80A13'>$secondsRemaining</font> second"*/
                //binding.tdResendOtp.text = getString(R.string.resend_otp,secondsRemaining)/*"Resend OTP after <font color='#B80A13'>$secondsRemaining</font> second"*/
                setTimerValue(secondsRemaining)
            }

            override fun onFinish() {
                val spannableString = SpannableStringBuilder()
                //authViewModel.timingValue.value =  "Resend OTP"
                //binding.tdResendOtp.text =  "Resend OTP"
                val firstPart = getString(R.string.resend_otp_title)
                spannableString.append(firstPart)
                spannableString.setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.logo_color)),
                    spannableString.length - firstPart.length,
                    spannableString.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                binding.tdResendOtp.text =spannableString
            }
        }


        countDownTimer.start()

    }
    private fun setTimerValue(secondsRemaining: Long) {
        // Create a SpannableStringBuilder
        val spannableString = SpannableStringBuilder()

        // Add the first part of the text with one color
        val firstPart = "Resend OTP after "
        spannableString.append(firstPart)
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.black)),
            spannableString.length - firstPart.length,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Add the second part of the text with a different color
        val secondPart = "$secondsRemaining"
        spannableString.append(secondPart)
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.pink2)),
            spannableString.length - secondPart.length,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.append("s")
        // Set the SpannableStringBuilder as the text for the TextView
        binding.tdResendOtp.text = spannableString
    }

    fun convertMillisToHMS(milliseconds: Long): String {
        val hours = milliseconds / 3600000
        val remainingMinutes = (milliseconds % 3600000) / 60000
        val remainingSeconds = (milliseconds % 60000) / 1000

        return String.format("%02d:%02d:%02d", hours, remainingMinutes, remainingSeconds)
    }
}