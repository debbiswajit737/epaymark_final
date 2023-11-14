package com.epaymark.epay.ui.fragment.tablayout


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.data.model.ContactModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentChangeTpinBinding
import com.epaymark.epay.databinding.FragmentSupportBinding
import com.epaymark.epay.ui.base.BaseFragment


class ChangeTPINPinFragment : BaseFragment() {
    lateinit var binding: FragmentChangeTpinBinding
    private val viewModel: MyViewModel by activityViewModels()
    var contactModelList = ArrayList<ContactModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_tpin, container, false)
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

    }

    fun setObserver() {
        binding.apply {

        }

    }




}