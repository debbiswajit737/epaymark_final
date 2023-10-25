package com.epaymark.epay.utils.helpers

import android.content.Context
import com.epaymark.epay.utils.helpers.Constants.EPAY_SHAREDFREFFRENCE
import com.epaymark.epay.utils.helpers.Constants.TEST

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreff @Inject constructor(@ApplicationContext private val context: Context?){

    fun setTestData(phn: String?) {

        context?.let {
            val settings =
                context.getSharedPreferences(EPAY_SHAREDFREFFRENCE, Context.MODE_PRIVATE)
            val editor = settings.edit()
            phn?.let { phone ->
                editor.putString(TEST, phone)
            }
            editor.apply()
        }
    }

    fun getTestData(): String {
        var phn: String? = null
        context?.let {
            val shrdprf =
                it.getSharedPreferences(EPAY_SHAREDFREFFRENCE, Context.MODE_PRIVATE)
            phn = shrdprf.getString(TEST, "0")
        }
        return phn ?: "0"

    }


}