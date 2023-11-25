package com.epaymark.epay.ui.receipt


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentMobileReceptDialogBinding
import com.epaymark.epay.ui.activity.DashboardActivity
import com.epaymark.epay.ui.base.BaseCenterSheetFragment
import com.epaymark.epay.utils.`interface`.CallBack
import java.io.FileOutputStream
import java.io.IOException


class MobileReceptDialogFragment() : BaseCenterSheetFragment() {
    lateinit var binding: FragmentMobileReceptDialogBinding
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mobile_recept_dialog, container, false)
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
            imgHome.backToHome()
            fabShare.setOnClickListener{
                shareImage()
            }
            imgHome.setOnClickListener{

                (activity as? DashboardActivity)?.let {
                    it.startActivity(Intent(it,DashboardActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                }
            }
          }
        }



    private fun shareImage() {

        activity?.let {
            binding.apply {

                var screenshotBitmap =cardView2.takeScreenshot()
                (activity as? DashboardActivity)?.shareImage(screenshotBitmap)

            }
        }


    }

    fun initView() {
        setCrdViewMinHeight()

    }

    private fun setCrdViewMinHeight() {



    }

    fun setObserver() {
        binding.apply {

        }

    }


}