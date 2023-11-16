package com.epaymark.epay.ui.fragment.paymentRequest


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.adapter.BankModeListAdapter
import com.epaymark.epay.data.model.BankModeListModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentPaymentModeBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.`interface`.CallBack

class PaymentRequestModeFragment : BaseFragment() {
    lateinit var binding: FragmentPaymentModeBinding
    private val viewModel: MyViewModel by activityViewModels()
    var bankInformationList = ArrayList<BankModeListModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_mode, container, false)
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
            //
            recycleViewPaymentMode.apply {
                bankInformationList.add(BankModeListModel(R.drawable.rounded_bank,"CASH - COUNTER DEPOSIT","Minimam Amount:100.00","Maximum Amount: 500000.00"))
                bankInformationList.add(BankModeListModel(R.drawable.rounded_bank,"CASH CDM","Minimam Amount:100.00","Maximum Amount: 500000.00"))
                bankInformationList.add(BankModeListModel(R.drawable.rounded_bank,"ONLINE SAME BANK","Minimam Amount:100.00","Maximum Amount: 500000.00"))
                bankInformationList.add(BankModeListModel(R.drawable.rounded_bank,"ONLINE - IMPS","Minimam Amount:100.00","Maximum Amount: 500000.00"))
                bankInformationList.add(BankModeListModel(R.drawable.rounded_bank,"ONLINE - NEFT","Minimam Amount:100.00","Maximum Amount: 500000.00"))
                bankInformationList.add(BankModeListModel(R.drawable.rounded_bank,"ONLINE - RTGS","Minimam Amount:100.00","Maximum Amount: 500000.00"))
                adapter= BankModeListAdapter(bankInformationList, object : CallBack {
                    override fun getValue(s: String) {
                        viewModel?.selectedBankMode?.value=s
                        findNavController().navigate(R.id.action_paymentRequestModeFragment_to_paymentRequestImformationFragment)
                    }

                })
            }
        }
    }

    fun setObserver() {

    }


}