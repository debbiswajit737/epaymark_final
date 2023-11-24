package com.epaymark.epay.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.R
import com.epaymark.epay.adapter.PhonePadAdapter
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.AadharAuthBottomsheetLayoutBinding
import com.epaymark.epay.databinding.TpinBottomsheetLayoutBinding
import com.epaymark.epay.ui.base.BaseBottomSheetFragment
import com.epaymark.epay.utils.`interface`.CallBack
import com.epaymark.epay.utils.`interface`.KeyPadOnClickListner

class AadharAuthBottomSheetDialog(val callBack: CallBack) :BaseBottomSheetFragment() {
    lateinit var binding: AadharAuthBottomsheetLayoutBinding
    private val myViewModel: MyViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.aadhar_auth_bottomsheet_layout, container, false)
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
        binding.apply {
            imgAadhar.setOnClickListener{
                callBack.getValue("ok")
                dismiss()
            }
        }

    }

    private fun setObserver() {

    }

    private fun initView() {
        binding.apply {

        }

    }



}