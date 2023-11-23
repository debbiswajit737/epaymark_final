package com.epaymark.epay.ui.base

import android.R
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment


open class PopUpFragment: DialogFragment() {

    fun ImageView.back(){
        this.setOnClickListener{
            dismiss()
        }
    }
}