package com.epaymark.epay.ui.popup


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentSuccessPopupBinding
import com.epaymark.epay.ui.base.PopUpFragment
import com.epaymark.epay.utils.`interface`.CallBack4


class SuccessPopupFragment(val callBack4:  CallBack4) : PopUpFragment() {
    lateinit var binding: FragmentSuccessPopupBinding
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_success_popup, container, false)
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
            buttonDismiss.setOnClickListener{
                callBack4.getValue4("","","","")
            }

          }
        }


    fun initView() {

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    fun setObserver() {
        binding.apply {

        }

    }


}