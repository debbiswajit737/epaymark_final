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

    val selectedBank = MutableLiveData<String>("")
    val selectedBankMode = MutableLiveData<String>("")
    val prepaitOrPostPaid = MutableLiveData<String>("")
    val reportType = MutableLiveData<String>("")
    val startDate = MutableLiveData<String>("")
    val enddate = MutableLiveData<String>("")
    val kycStatus = MutableLiveData<String>("KYC Approved")


    val tPin = MutableLiveData<String>("")
    val state = MutableLiveData<String>()
    val billerAddress = MutableLiveData<String>()

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

    val consumerId = MutableLiveData<String>()
    val consumerIdPrice = MutableLiveData<String>()
    val oldPin = MutableLiveData<String>()
    val newPin = MutableLiveData<String>()
    val confirmPin = MutableLiveData<String>()

    val oldTPin = MutableLiveData<String>()
    val newTPin = MutableLiveData<String>()
    val confirmTPin = MutableLiveData<String>()
    val epotly_mobile = MutableLiveData<String>()
    val epotly_amt = MutableLiveData<String>()
    val cash_withdraw_pin_code = MutableLiveData<String>()
    val cash_withdraw_pan = MutableLiveData<String>()


    val mobileError = MutableLiveData<String>()
    val operatorError = MutableLiveData<String>()
    val amtError = MutableLiveData<String>()
    val subIdError = MutableLiveData<String>()
    val dthError = MutableLiveData<String>()
    val dthAmtError = MutableLiveData<String>()
    val epotly_amt_Error = MutableLiveData<String>()

    val consumerIdError = MutableLiveData<String>()
    val consumerIdPriceError = MutableLiveData<String>()
    val oldPinError = MutableLiveData<String>()
    val newPinError = MutableLiveData<String>()
    val confirmPinError = MutableLiveData<String>()

    val oldTPinError = MutableLiveData<String>()
    val newTPinError = MutableLiveData<String>()
    val confirmTPinError = MutableLiveData<String>()
    val epotly_mobileError = MutableLiveData<String>()
    val cash_withdraw_pin_codeError = MutableLiveData<String>()
    val cash_withdraw_pan_codeError = MutableLiveData<String>()


    val mobileErrorVisible = MutableLiveData<Boolean>()
    val operatorErrorVisible = MutableLiveData<Boolean>()
    val amtErrorErrorVisible = MutableLiveData<Boolean>()
    val subIdErrorVisible = MutableLiveData<Boolean>()
    val dthErrorErrorVisible = MutableLiveData<Boolean>()
    val dthAmtErrorVisible = MutableLiveData<Boolean>()

    val consumerIdErrorVisible = MutableLiveData<Boolean>()
    val consumerIdPriceErrorVisible = MutableLiveData<Boolean>()
    val oldPinErrorVisible = MutableLiveData<Boolean>()
    val newPinErrorVisible = MutableLiveData<Boolean>()
    val confirmPinErrorVisible = MutableLiveData<Boolean>()

    val oldTPinErrorVisible = MutableLiveData<Boolean>()
    val newTPinErrorVisible = MutableLiveData<Boolean>()
    val confirmTPinErrorVisible = MutableLiveData<Boolean>()
    val epotly_mobileErrorVisible = MutableLiveData<Boolean>()
    val epotly_amt_ErrorVisible = MutableLiveData<Boolean>()
    val cash_withdraw_pin_codeErrorVisible = MutableLiveData<Boolean>()
    val cash_withdraw_pan_codeErrorVisible = MutableLiveData<Boolean>()



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


    fun electricValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (consumerId.value?.trim().isNullOrBlank()) {
            consumerIdError.value = "Please enter a valid Customer Id fixed 11 digit"
            consumerIdErrorVisible.value = true
            isValid = false
        } else {
            consumerIdError.value = ""
            consumerIdErrorVisible.value = false
        }

        if (consumerIdPrice.value?.trim().isNullOrBlank()) {
            consumerIdPriceError.value = "Please select amount"
            consumerIdPriceErrorVisible.value = true
            isValid = false
        } else {
            consumerIdPriceError.value = ""
            consumerIdPriceErrorVisible.value = false
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



    fun changeLoginPinValidation(): Boolean {
        invisibleErrorTexts()

        var isValid = true

        // Validate Old Password
        if (oldPin.value?.trim().isNullOrBlank()) {
            oldPinError.value = "Please enter the Old PIN"
            oldPinErrorVisible.value = true
            isValid = false
        } else {
            oldPinErrorVisible.value = false
        }

        // Validate New Password
        if (newPin.value?.trim().isNullOrBlank()) {
            newPinError.value = "Please enter the New PIN"
            newPinErrorVisible.value = true
            isValid = false
        } else {
            newPinErrorVisible.value = false
        }

        // Validate Confirm Password
        if (confirmPin.value?.trim().isNullOrBlank()) {
            confirmPinError.value = "Please enter the Confirm PIN"
            confirmPinErrorVisible.value = true
            isValid = false
        } else if (confirmPin.value != newPin.value) {
            confirmPinError.value = "PINs do not match"
            confirmPinErrorVisible.value = true
            isValid = false
        } else {
            confirmPinErrorVisible.value = false
        }

        return isValid
    }

    fun changeLoginTPinValidation(): Boolean {
        invisibleErrorTexts()

        var isValid = true

        // Validate Old Password
        if (oldTPin.value?.trim().isNullOrBlank()) {
            oldTPinError.value = "Please enter the Old TPIN"
            oldTPinErrorVisible.value = true
            isValid = false
        } else {
            oldTPinErrorVisible.value = false
        }

        // Validate New Password
        if (newTPin.value?.trim().isNullOrBlank()) {
            newTPinError.value = "Please enter the New TPIN"
            newTPinErrorVisible.value = true
            isValid = false
        } else {
            newTPinErrorVisible.value = false
        }

        // Validate Confirm Password
        if (confirmTPin.value?.trim().isNullOrBlank()) {
            confirmTPinError.value = "Please enter the Confirm TPIN"
            confirmTPinErrorVisible.value = true
            isValid = false
        } else if (confirmTPin.value != newTPin.value) {
            confirmTPinError.value = "TPIN do not match"
            confirmTPinErrorVisible.value = true
            isValid = false
        } else {
            confirmTPinErrorVisible.value = false
        }

        return isValid
    }


    fun epotlyValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (epotly_mobile.value?.trim().isNullOrBlank()) {
            epotly_mobileError.value = "Mobile number is required"
            epotly_mobileErrorVisible.value = true
            isValid = false
        } else {
            if (mobile.value?.trim()?.validate("mobile") == false) {
                epotly_mobileError.value = "Mobile number is not valid"
                epotly_mobileErrorVisible.value = true
                isValid = false
            } else {
                epotly_mobileError.value = ""
                epotly_mobileErrorVisible.value = false
            }
        }

        if (epotly_amt.value?.trim().isNullOrBlank()) {
            epotly_amt_Error.value = "Please enter amount"
            epotly_amt_ErrorVisible.value = true
            isValid = false
        } else {
            epotly_amt_Error.value = ""
            epotly_amt_ErrorVisible.value = false
        }



        return isValid
    }


    fun cashWithdrawValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (cash_withdraw_pin_code.value?.trim().isNullOrBlank()) {
            cash_withdraw_pin_codeError.value = "Pin code is required"
            cash_withdraw_pin_codeErrorVisible.value = true
            isValid = false
        } else {
            if (cash_withdraw_pin_code.value?.trim()?.validate("pincode")==false) {
                cash_withdraw_pin_codeError.value = "PIN code is not valid"
                cash_withdraw_pin_codeErrorVisible.value = true
                isValid = false
            } else {
                cash_withdraw_pin_codeError.value = ""
                cash_withdraw_pin_codeErrorVisible.value = false
            }
        }



        if (cash_withdraw_pan.value?.trim().isNullOrBlank()) {
            cash_withdraw_pan_codeError.value = "Pan card number is required"
            cash_withdraw_pan_codeErrorVisible.value = true
            isValid = false
        } else {
            if (cash_withdraw_pan.value?.trim()?.validate("pan")==false) {
                cash_withdraw_pan_codeError.value = "Pan card number is required"
                cash_withdraw_pan_codeErrorVisible.value = true
                isValid = false
            } else {
                cash_withdraw_pan_codeError.value = ""
                cash_withdraw_pan_codeErrorVisible.value = false
            }
        }






        return isValid
    }



    fun invisibleErrorTexts() {
        mobileErrorVisible.value = false
        operatorErrorVisible.value = false
        amtErrorErrorVisible.value = false


         subIdErrorVisible.value = false
         dthErrorErrorVisible.value = false
         dthAmtErrorVisible.value = false

         consumerIdErrorVisible.value = false
         consumerIdPriceErrorVisible.value = false


        oldPinErrorVisible.value = false
        newPinErrorVisible.value = false
        confirmPinErrorVisible.value = false

        oldTPinErrorVisible.value = false
        newTPinErrorVisible.value = false
        confirmTPinErrorVisible.value = false

    }
}