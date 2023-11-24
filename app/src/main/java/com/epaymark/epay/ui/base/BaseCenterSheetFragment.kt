package com.epaymark.epay.ui.base

import android.R
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.ui.activity.DashboardActivity


open class BaseCenterSheetFragment: DialogFragment() {


    override fun getTheme(): Int {
        return R.style.Theme_NoTitleBar_Fullscreen
    }
    fun ImageView.back(){
        this.setOnClickListener{
            dismiss()
        }
    }

    fun ImageView.backToHome(){
        this.setOnClickListener{
            (activity as? DashboardActivity)?.navigate()

            //findNavController().popBackStack(com.epaymark.epay.R.id.homeFragment2,false)
        }
    }


}