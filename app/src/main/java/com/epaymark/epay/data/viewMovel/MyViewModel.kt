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
    val state = MutableLiveData<String>()

    val userName = MutableLiveData<String>()
    val balence = MutableLiveData<String>()
    val nextRecharge = MutableLiveData<String>()
    val monthly = MutableLiveData<String>()
    val type = MutableLiveData<String>()


    val prepaitIsActive = MutableLiveData<Boolean>()
    val postpaitIsActive = MutableLiveData<Boolean>()
    val operatorName = MutableLiveData<String>()


    val mobile = MutableLiveData<String>()
    val operator = MutableLiveData<String>()
    val amt = MutableLiveData<String>()
    val subId = MutableLiveData<String>()
    val dthOperator = MutableLiveData<String>()
    val dthAmt = MutableLiveData<String>()


    val mobileError = MutableLiveData<String>()
    val operatorError = MutableLiveData<String>()
    val amtError = MutableLiveData<String>()
    val subIdError = MutableLiveData<String>()
    val dthError = MutableLiveData<String>()
    val dthAmtError = MutableLiveData<String>()


    val mobileErrorVisible = MutableLiveData<Boolean>()
    val operatorErrorVisible = MutableLiveData<Boolean>()
    val amtErrorErrorVisible = MutableLiveData<Boolean>()
    val subIdErrorVisible = MutableLiveData<Boolean>()
    val dthErrorErrorVisible = MutableLiveData<Boolean>()
    val dthAmtErrorVisible = MutableLiveData<Boolean>()



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

    fun dthValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (subId.value?.trim().isNullOrBlank()) {
            subIdError.value = "Please enter a valid DTH subscriber ID."
            subIdErrorVisible.value = true
            isValid = false
        } else {
            subIdError.value = ""
            subIdErrorVisible.value = false
        }

        if (dthOperator.value?.trim().isNullOrBlank()) {
            dthError.value = "Please select an operator"
            dthErrorErrorVisible.value = true
            isValid = false
        } else {
            dthError.value = ""
            dthErrorErrorVisible.value = false
        }

        if (dthAmt.value?.trim().isNullOrBlank()) {
            dthAmtError.value = "Please enter a valid amount."
            dthAmtErrorVisible.value = true
            isValid = false
        } else {
            dthAmtError.value = ""
            dthAmtErrorVisible.value = false
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