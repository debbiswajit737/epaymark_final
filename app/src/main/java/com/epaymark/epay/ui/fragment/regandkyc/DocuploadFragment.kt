package com.epaymark.epay.ui.fragment.regandkyc


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.epaymark.epay.R
import com.epaymark.epay.databinding.FragmentDocuploadBinding
import com.epaymark.epay.ui.base.BaseFragment

class DocuploadFragment : BaseFragment() {
    lateinit var binding: FragmentDocuploadBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_docupload, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
    }

    fun initView() {

    }

    fun setObserver() {

    }
}