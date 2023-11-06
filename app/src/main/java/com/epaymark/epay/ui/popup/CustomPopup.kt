package com.epaymark.epay.ui.popup

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.epaymark.epay.data.model.UserInfoModel
import com.epaymark.epay.databinding.PopupDthUserDetailsBinding


object CustomPopup {

    fun showBindingPopup(context: Context, userInfoModel: UserInfoModel) {
        val binding = PopupDthUserDetailsBinding.inflate(LayoutInflater.from(context))

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(binding.root)
        binding.model=userInfoModel


        /*alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }*/

        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
        binding.tvOk.setOnClickListener {
            alertDialog.dismiss()
        }
    }


}