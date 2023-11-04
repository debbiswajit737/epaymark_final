package com.epaymark.epay.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.adapter.OperatorAdapter
import com.epaymark.epay.data.model.OperatorModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.OperatorBottomsheetLayoutBinding
import com.epaymark.epay.ui.base.BaseBottomSheetFragment
import com.epaymark.epay.utils.`interface`.CallBack

class OperatorDialog(val callBack: CallBack) :BaseBottomSheetFragment() {
    lateinit var binding: OperatorBottomsheetLayoutBinding
    private val viewModel: MyViewModel by activityViewModels()
    var operator = ArrayList<OperatorModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.operator_bottomsheet_layout, container, false)
        binding.viewModel = viewModel
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
        binding.apply {

        }

    }

    private fun setObserver() {

    }

    private fun initView() {

        binding.recycleOperator.apply {
            operator.clear()
            operator.add(OperatorModel(R.drawable.airtel,"AIRTEL",false))
            operator.add(OperatorModel(R.drawable.bharat_sanchar_nigam_limited_logo_wine,"BSNL TOPUP",false))
            operator.add(OperatorModel(R.drawable.bharat_sanchar_nigam_limited_logo_wine,"BSNL- VALIDITY",false))
            operator.add(OperatorModel(R.drawable.jio,"RELIANCE JIO",false))
            operator.add(OperatorModel(R.drawable.vi,"VI",false))

            adapter= OperatorAdapter(operator, object : CallBack {
                override fun getValue(s: String) {
                    callBack.getValue(s)
                    dismiss()
                }

            })
        }
    }

}