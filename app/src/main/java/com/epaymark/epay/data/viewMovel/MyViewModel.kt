package com.epaymark.epay.data.viewMovel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epaymark.epay.data.genericmodel.BaseResponse
import com.epaymark.epay.data.model.sample.Test
import com.epaymark.epay.network.ResponseState

import com.epaymark.epay.repository.DeliveryOptionsRepository
import com.epaymark.epay.utils.helpers.helper.validate
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: DeliveryOptionsRepository) : ViewModel() {
    val prepaitIsActive = MutableLiveData<Boolean>()
    val postpaitIsActive = MutableLiveData<Boolean>()
    val operatorName = MutableLiveData<String>()


    val mobile = MutableLiveData<String>()
    val operator = MutableLiveData<String>()
    val amt = MutableLiveData<String>()


    val mobileError = MutableLiveData<String>()
    val operatorError = MutableLiveData<String>()
    val amtError = MutableLiveData<String>()


    val mobileErrorVisible = MutableLiveData<Boolean>()
    val operatorErrorVisible = MutableLiveData<Boolean>()
    val amtErrorErrorVisible = MutableLiveData<Boolean>()




    fun regValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (mobile.value?.trim().isNullOrBlank()) {
            mobileError.value = "Mobile number is required"
            mobileErrorVisible.value = true
            isValid = false
        } else {
            if (mobile.value?.trim()?.validate("mobile") == false) {
                mobileError.value = "Mobile number is not valid"
                mobileErrorVisible.value = true
                isValid = false
            } else {
                mobileError.value = ""
                mobileErrorVisible.value = false
            }
        }

        if (amt.value?.trim().isNullOrBlank()) {
            amtError.value = "Please enter amount"
            amtErrorErrorVisible.value = true
            isValid = false
        } else {
            amtError.value = ""
            amtErrorErrorVisible.value = false
        }

        if (operator.value?.trim().isNullOrBlank()) {
            operatorError.value = "Please select operator"
            operatorErrorVisible.value = true
            isValid = false
        } else {
            operatorError.value = ""
            operatorErrorVisible.value = false
        }



        return isValid
    }



        val loginResponseLiveData: LiveData<ResponseState<BaseResponse<Test>>>
        get() = repository.loginResponseLiveData
    fun login(requestBody: JsonObject) {
        viewModelScope.launch {
            repository.login(requestBody)
        }
    }






    fun invisibleErrorTexts() {
        mobileErrorVisible.value = false
        operatorErrorVisible.value = false
        amtErrorErrorVisible.value = false
    }
}