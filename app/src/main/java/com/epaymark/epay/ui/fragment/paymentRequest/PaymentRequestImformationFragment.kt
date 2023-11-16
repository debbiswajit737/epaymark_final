package com.epaymark.epay.ui.fragment.paymentRequest


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentPaymentRequestBinding
import com.epaymark.epay.databinding.FragmentPaymentRequestImformationBinding
import com.epaymark.epay.ui.base.BaseFragment

class PaymentRequestImformationFragment : BaseFragment() {
    lateinit var binding: FragmentPaymentRequestImformationBinding
    private val viewModel: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_request_imformation, container, false)
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






        }



    }

    fun initView() {
        binding.apply {

        }
    }

    fun setObserver() {

    }


}