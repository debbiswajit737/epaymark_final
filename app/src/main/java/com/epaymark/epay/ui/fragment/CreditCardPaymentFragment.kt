package com.epaymark.epay.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentCreditCardPaymentBinding
import com.epaymark.epay.databinding.FragmentEpotlyBinding
import com.epaymark.epay.databinding.FragmentMobileRechargeBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.Constants.isDthOperator
import com.epaymark.epay.utils.`interface`.CallBack

class CreditCardPaymentFragment : BaseFragment() {
    lateinit var binding: FragmentCreditCardPaymentBinding
    private val viewModel: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_credit_card_payment, container, false)
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




            btnSubmit.setOnClickListener{
                activity?.let {act->
                    if (viewModel?.creditValidation() == true){
                        findNavController().navigate(R.id.transactionOtpFragment)
                    }
                }

            }

        }



    }

    fun initView() {
        binding.apply {
            etAmt.setupAmount()
        }
    }

    fun setObserver() {
        binding.apply {
            etRemarks.oem(btnSubmit)
        }
    }


}