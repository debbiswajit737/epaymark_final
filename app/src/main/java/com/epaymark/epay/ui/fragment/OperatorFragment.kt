package com.epaymark.epay.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.adapter.OperatorAdapter
import com.epaymark.epay.data.model.OperatorModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.OperatorFragmentLayoutBinding

import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.`interface`.CallBack

class OperatorFragment : BaseFragment() {
    lateinit var binding: OperatorFragmentLayoutBinding
    private val viewModel: MyViewModel by activityViewModels()
    var operator = ArrayList<OperatorModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.operator_fragment_layout, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
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
            imgBack.back()
        }

    }

    private fun setObserver() {

    }

    private fun initView() {

        binding.recycleOperator.apply {
            operator.clear()
            operator.add(OperatorModel(R.drawable.airtel_com_logo,"Airtel",false))
            operator.add(OperatorModel(R.drawable.bharat_sanchar_logo,"BSNL Topup",false))
            operator.add(OperatorModel(R.drawable.bharat_sanchar_logo,"BSNL-Validity",false))
            operator.add(OperatorModel(R.drawable.jio,"Reliance Jio",false))
            operator.add(OperatorModel(R.drawable.vi,"VI",false))

            adapter= OperatorAdapter(operator, object : CallBack {
                override fun getValue(s: String) {
                    viewModel?.apply {
                        operator.value=s
                        dthOperator.value =s
                    }

                   // callBack.getValue(s)
                    findNavController().popBackStack()
                }

            })
        }
    }

}