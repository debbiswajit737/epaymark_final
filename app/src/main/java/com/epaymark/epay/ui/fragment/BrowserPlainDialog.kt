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
import com.epaymark.epay.adapter.BrowserAdapter
import com.epaymark.epay.data.model.BrowserModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.BrowserBottomsheetLayoutBinding
import com.epaymark.epay.ui.base.BaseBottomSheetFragment
import com.epaymark.epay.utils.`interface`.CallBack

class BrowserPlainDialog(val callBack: CallBack) :BaseBottomSheetFragment() {
    lateinit var binding: BrowserBottomsheetLayoutBinding
    private val viewModel: MyViewModel by activityViewModels()
    var browser = ArrayList<BrowserModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.browser_bottomsheet_layout, container, false)
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

        binding.recycleBrowser.apply {
            browser.clear()
            browser.add(BrowserModel("152","Truly Unlimited","1GB","300",false))
            browser.add(BrowserModel("199","Truly Unlimited","1GB","300",false))
            browser.add(BrowserModel("1000","Truly Unlimited","1GB","300",false))
            browser.add(BrowserModel("2199","Truly Unlimited","1GB","300",false))
            browser.add(BrowserModel("3000","Truly Unlimited","1GB","300",false))

            adapter= BrowserAdapter(browser, object : CallBack {
                override fun getValue(s: String) {
                    callBack.getValue(s)
                    dismiss()
                }

            })
        }
    }

}