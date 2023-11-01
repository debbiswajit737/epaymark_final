package com.epaymark.epay.data.bindingAdapter

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.ViewModel
import com.epaymark.epay.data.viewMovel.AuthViewModel


@BindingAdapter("app:hideErrorTextView")
fun hideErrorTextView(view: TextView, isVisible: Boolean) {
    if (isVisible)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.INVISIBLE
}


@BindingAdapter("app:requestFocus")
fun requestFocus(view: EditText, requestFocus: Boolean) {
    if (requestFocus)
        view.requestFocus()
}
@BindingAdapter("app:addTextChangeListener")
fun addTextChangeListener(view: EditText, viewModel: ViewModel) {

    view.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            when (viewModel) {
                is AuthViewModel -> viewModel.invisibleErrorTexts()

            }
        }

    })

}
