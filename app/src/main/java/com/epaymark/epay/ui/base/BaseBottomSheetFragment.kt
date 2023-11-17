package com.epaymark.epay.ui.base

import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.epaymark.epay.R
import com.epaymark.epay.databinding.FragmentDocuploadBinding
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.DecimalDigitsInputFilter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseBottomSheetFragment: BottomSheetDialogFragment() {
    fun EditText.setupAmount() {
        /*val inputFilter = InputFilter { source, start, end, dest, dstart, dend ->
            // Define your filter logic here
            val newText = dest.toString().substring(0, dstart) + source.subSequence(start, end) + dest.toString().substring(dend)
            if (newText.matches(Regex("^\\d{0,7}(\\.\\d{0,2})?\$"))) {
                null // Accept the input
            } else {
                "" // Reject the input
            }
        }*/

        //this.filters = arrayOf(inputFilter)
        this.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(Constants.INPUT_FILTER_MAX_VALUE, Constants.INPUT_FILTER_POINTER_LENGTH))

    }

}