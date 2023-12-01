package com.epaymark.epay.ui.fragment.fragmentDialog

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.R
import com.epaymark.epay.adapter.BankListAdapter
import com.epaymark.epay.adapter.FastTagBankListAdapter
import com.epaymark.epay.adapter.PhonePadAdapter
import com.epaymark.epay.data.model.BankListModel
import com.epaymark.epay.data.model.FastTagBankListModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.BankListBottomsheetLayoutBinding
import com.epaymark.epay.databinding.BankListLayoutBinding
import com.epaymark.epay.databinding.BankListNameBottomsheetLayoutBinding
import com.epaymark.epay.databinding.TpinBottomsheetLayoutBinding
import com.epaymark.epay.ui.base.BaseBottomSheetFragment
import com.epaymark.epay.utils.`interface`.CallBack
import com.epaymark.epay.utils.`interface`.CallBack2
import com.epaymark.epay.utils.`interface`.CallBack4
import com.epaymark.epay.utils.`interface`.KeyPadOnClickListner

class BankListOnlyNameBottomSheetDialog(val callBack: CallBack) :BaseBottomSheetFragment() {
    lateinit var binding: BankListNameBottomsheetLayoutBinding
    private val myViewModel: MyViewModel by activityViewModels()
    var fastTagBankList = ArrayList<FastTagBankListModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bank_list_name_bottomsheet_layout, container, false)
        binding.viewModel = myViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun getTheme(): Int {
        return R.style.SheetDialog
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
        onViewClick()
    }

    private fun onViewClick() {
        binding.apply {}

    }

    private fun setObserver() {

    }

    private fun initView() {
        binding.apply {
            binding.apply {
                /*recycleViewPaymentRequest.apply {
                    bankList.add(BankListModel(R.drawable.axix_bank_logo,"AXIX BANK","A/C:91022112121212","IFSC:UTIB0000669"))
                    bankList.add(BankListModel(R.drawable.axix_bank_logo,"AXIX BANK","A/C:91022112121212","IFSC:UTIB0000669"))
                    bankList.add(BankListModel(R.drawable.axix_bank_logo,"AXIX BANK","A/C:91022112121212","IFSC:UTIB0000669"))
                    bankList.add(BankListModel(R.drawable.axix_bank_logo,"AXIX BANK","A/C:91022112121212","IFSC:UTIB0000669"))
                    bankList.add(BankListModel(R.drawable.axix_bank_logo,"AXIX BANK","A/C:91022112121212","IFSC:UTIB0000669"))
                    bankList.add(BankListModel(R.drawable.axix_bank_logo,"AXIX BANK","A/C:91022112121212","IFSC:UTIB0000669"))
                    adapter= BankListAdapter(bankList, object : CallBack4 {
                        override fun getValue4(s1: String, s2: String, s3: String, s4: String) {
                            viewModel?.beneficiary_bank_name?.value=s1
                            viewModel?.beneficiary_ifsc?.value=s2
                            dismiss()
                        }




                    })
                }*/

                recycleViewPaymentRequest.apply {
                    fastTagBankList.add(FastTagBankListModel(R.drawable.axix_bank_logo,"Axis Bank"))
                    fastTagBankList.add(FastTagBankListModel(R.drawable.icici,"ICICI Bank"))
                    adapter= FastTagBankListAdapter(fastTagBankList, object : CallBack4 {
                        override fun getValue4(s1: String, s2: String, s3: String, s4: String) {
                            viewModel?.selectBank?.value=s1
                            dismiss()
                        }




                    })
                }
            }
        }

    }


}