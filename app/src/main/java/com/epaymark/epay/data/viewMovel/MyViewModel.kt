package com.epaymark.epay.data.viewMovel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epaymark.epay.R
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


    val selectedButton = MutableLiveData<Int>()





    val from_page_message = MutableLiveData<String>()
    val popup_message = MutableLiveData<String>("Success!")
    val receiveStatus = MutableLiveData<String>("")
    val receiveReceptMessahe = MutableLiveData<String>("")
    val loginPin = MutableLiveData<String>("")
    val otp: MutableLiveData<String> = MutableLiveData("")
    var otpMobile=MutableLiveData<String>("")
    var sendMoneyVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    var filePath: MutableLiveData<Uri> = MutableLiveData()
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

    val IMPSIsActive = MutableLiveData<Boolean>()
    val NEFTIsActive = MutableLiveData<Boolean>()


    val operatorName = MutableLiveData<String>()


    val mobile = MutableLiveData<String>()
    val operator = MutableLiveData<String>()
    val amt = MutableLiveData<String>()
    val subId = MutableLiveData<String>()
    val dthOperator = MutableLiveData<String>()
    val selectrdOperator = MutableLiveData<String>()
    val selectrdBroadbandOperator = MutableLiveData<String>()
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
    val paymentAmt = MutableLiveData<String>()
    val particular = MutableLiveData<String>()
    val amtMoveToWallet = MutableLiveData<String>()
    val amtMoveToPayabhi = MutableLiveData<String>()

    val amtMoveToBank = MutableLiveData<String>()
    val mobileSendMoney = MutableLiveData<String>()
    val nameSendMoney = MutableLiveData<String>()

    val credit_card = MutableLiveData<String>()
    val credit_holder_name = MutableLiveData<String>()
    val credit_remarks = MutableLiveData<String>()
    val select_card_type = MutableLiveData<String>()
    val credit_mobile = MutableLiveData<String>()
    val credit_amt = MutableLiveData<String>()


    val beneficiary_bank_name = MutableLiveData<String>()
    val beneficiary_ifsc = MutableLiveData<String>()
    val beneficiary_acc = MutableLiveData<String>()
    val beneficiary_name = MutableLiveData<String>()


    val provided_amt = MutableLiveData<String>()
    val provided_aadhar_number = MutableLiveData<String>()
    val provided_customer_number = MutableLiveData<String>()

    val vehicleRegId = MutableLiveData<String>()
    val fastTagOperator = MutableLiveData<String>()
    val fastTagAmt = MutableLiveData<String>()

    val balence_enquary_aadhar_number = MutableLiveData<String>()
    val balence_enquary_customer_number = MutableLiveData<String>()

    val selectBank = MutableLiveData<String>()
    val loanAccountNumber = MutableLiveData<String>()

    val selectOperator = MutableLiveData<String>()
    val selectImage = MutableLiveData<Int>()

    val operatorSubId = MutableLiveData<String>()
    val operatordthAmt = MutableLiveData<String>()

    val instituteName = MutableLiveData<String>()
    val selectInstitute = MutableLiveData<String>()
    val educationAmt = MutableLiveData<String>()
    val loanAmount = MutableLiveData<String>()
    val education_bank_name = MutableLiveData<String>()
    val education_bank_ifsc = MutableLiveData<String>()


    val gasbookingNumber = MutableLiveData<String>()
    val gasBiller = MutableLiveData<String>()
    val gasBookingAmt = MutableLiveData<String>()



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

    val paymentAmtError = MutableLiveData<String>()
    val depositeDate = MutableLiveData<String>()
    val depositeDateError = MutableLiveData<String>()
    val particularError = MutableLiveData<String>()
    val amtMoveToWalletError = MutableLiveData<String>()
    val amtMoveToPayabhiError = MutableLiveData<String>()
    val amtMoveToBankError = MutableLiveData<String>()
    val amtMoveToBankModeError = MutableLiveData<String>()
    val mobileSendMoneyError = MutableLiveData<String>()
    val nameSendMoneyError = MutableLiveData<String>()

    val credit_cardError = MutableLiveData<String>()
    val credit_holder_name_Error = MutableLiveData<String>()
    val credit_remarks_Error = MutableLiveData<String>()
    val credit_mobile_Error = MutableLiveData<String>()
    val credit_amt_Error = MutableLiveData<String>()

    val beneficiary_bank_name_Error = MutableLiveData<String>()
    val beneficiary_ifsc_Error = MutableLiveData<String>()
    val beneficiary_accError = MutableLiveData<String>()
    val beneficiary_nameError = MutableLiveData<String>()

    val provided_amt_Error = MutableLiveData<String>()
    val provided_aadhar_numberError = MutableLiveData<String>()
    val provided_customer_numberError = MutableLiveData<String>()

    val fastTagOperatorError = MutableLiveData<String>()
    val vehicleRegIdError = MutableLiveData<String>()
    val fastTagAmtError = MutableLiveData<String>()

    val balence_enquary_aadhar_numberError = MutableLiveData<String>()
    val balence_enquary_customer_numberError = MutableLiveData<String>()

    val selectBankError = MutableLiveData<String>()
    val loanAccountNumberError = MutableLiveData<String>()
    val loanAmountError = MutableLiveData<String>()



    val selectOperatorError = MutableLiveData<String>()
    val operatorSubIdError = MutableLiveData<String>()
    val operatordthAmtError = MutableLiveData<String>()

    val instituteNameError = MutableLiveData<String>()
    val selectInstituteError = MutableLiveData<String>()
    val education_bank_nameError = MutableLiveData<String>()
    val educationAmtError = MutableLiveData<String>()



    val gasbookingNumberError = MutableLiveData<String>()
    val gasBillerError = MutableLiveData<String>()
    val gasBookingAmtError = MutableLiveData<String>()


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

    val paymentAmtErrorVisible = MutableLiveData<Boolean>()
    val depositeDateErrorVisible = MutableLiveData<Boolean>()
    val particularErrorVisible = MutableLiveData<Boolean>()
    val amtMoveToWalletErrorVisible = MutableLiveData<Boolean>()
    val amtMoveToPayabhiErrorVisible = MutableLiveData<Boolean>()
    val amtMoveToBankErrorVisible = MutableLiveData<Boolean>()
    val bankModeMoveToBankErrorVisible = MutableLiveData<Boolean>()
    val mobileSendMoneyErrorVisible = MutableLiveData<Boolean>()
    val nameSendMoneyErrorVisible = MutableLiveData<Boolean>()

    val credit_cardErrorVisible = MutableLiveData<Boolean>()
    val credit_holder_name_ErrorVisible = MutableLiveData<Boolean>()
    val credit_remarks_ErrorVisible = MutableLiveData<Boolean>()
    var select_card_type_ErrorVisible = MutableLiveData<Boolean>()
    val credit_mobileErrorVisible = MutableLiveData<Boolean>()
    val credit_amt_ErrorVisible = MutableLiveData<Boolean>()

    val beneficiary_bank_name_ErrorVisible = MutableLiveData<Boolean>()
    val beneficiary_ifsc_ErrorVisible = MutableLiveData<Boolean>()
    val beneficiary_accErrorVisible = MutableLiveData<Boolean>()
    val beneficiary_nameErrorVisible = MutableLiveData<Boolean>()

    val provided_amt_ErrorVisible = MutableLiveData<Boolean>()
    val provided_aadhar_numberErrorVisible = MutableLiveData<Boolean>()
    val provided_customer_numberErrorVisible = MutableLiveData<Boolean>()

    val vehicleRegIdErrorVisible = MutableLiveData<Boolean>()
    val fastTagOperatorErrorVisible = MutableLiveData<Boolean>()
    val fastTagAmtErrorVisible = MutableLiveData<Boolean>()

    val bank_check_ErrorVisible = MutableLiveData<Boolean>()


    val balence_enquary_aadhar_numberErrorVisible = MutableLiveData<Boolean>()
    val balence_enquary_customer_numberErrorVisible = MutableLiveData<Boolean>()

    val selectBankErrorVisible = MutableLiveData<Boolean>()
    val loanAccountNumberErrorVisible = MutableLiveData<Boolean>()
    val loanAmountErrorVisible = MutableLiveData<Boolean>()

    val selectOperatorErrorVisible = MutableLiveData<Boolean>()
    val operatorSubIdErrorVisible = MutableLiveData<Boolean>()
    val operatordthAmtErrorVisible = MutableLiveData<Boolean>()

    val instituteNameErrorVisible = MutableLiveData<Boolean>()
    val education_bank_nameErrorVisible = MutableLiveData<Boolean>()
    val selectInstituteErrorVisible = MutableLiveData<Boolean>()
    val educationAmtErrorVisible = MutableLiveData<Boolean>()


    val gasbookingNumberErrorVisible = MutableLiveData<Boolean>()
    val gasBillerErrorVisible = MutableLiveData<Boolean>()
    val gasBookingAmtErrorVisible = MutableLiveData<Boolean>()

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
            if (epotly_mobile.value?.trim()?.validate("mobile") == false) {
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


    fun PaymentrequestValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (paymentAmt.value?.trim().isNullOrBlank()) {
            paymentAmtError.value = "This field is required"
            paymentAmtErrorVisible.value = true
            isValid = false
        } else {
            paymentAmtError.value = ""
            paymentAmtErrorVisible.value = false
        }




        if (depositeDate.value?.trim().isNullOrBlank()) {
            depositeDateError.value = "This field is required"
            depositeDateErrorVisible.value = true
            isValid = false
        } else {
            depositeDateError.value = ""
            depositeDateErrorVisible.value = false
        }

        if (particular.value?.trim().isNullOrBlank()) {
            particularError.value = "This field is required"
            particularErrorVisible.value = true
            isValid = false
        } else {
            particularError.value = ""
            particularErrorVisible.value = false
        }

        return isValid
    }
    fun settleWalletValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (amtMoveToWallet.value?.trim().isNullOrBlank()) {
            amtMoveToWalletError.value = "This field is required"
            amtMoveToWalletErrorVisible.value = true
            isValid = false
        } else {
            amtMoveToWalletError.value = ""
            amtMoveToWalletErrorVisible.value = false
        }


        return isValid
    }
    fun payabhiValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (amtMoveToPayabhi.value?.trim().isNullOrBlank()) {
            amtMoveToPayabhiError.value = "This field is required"
            amtMoveToPayabhiErrorVisible.value = true
            isValid = false
        } else {
            amtMoveToPayabhiError.value = ""
            amtMoveToPayabhiErrorVisible.value = false
        }


        return isValid
    }


    fun MoveToBankValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (amtMoveToBank.value?.trim().isNullOrBlank()) {
            amtMoveToBankError.value = "This field is required"
            amtMoveToBankErrorVisible.value = true
            isValid = false
        } else {
            amtMoveToBankError.value = ""
            amtMoveToBankErrorVisible.value = false
        }

        if (IMPSIsActive?.value==false && NEFTIsActive?.value==false){
            isValid = false
            amtMoveToBankModeError.value="Please select a valid bank transaction mode."
            bankModeMoveToBankErrorVisible.value=true
        }
        else{

            amtMoveToBankModeError.value=""
            bankModeMoveToBankErrorVisible.value=false
        }


        return isValid
    }

    fun MoneyTranspherValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true


        if (mobileSendMoney.value?.trim().isNullOrBlank()) {
            mobileSendMoneyError.value = "Mobile number is required"
            mobileSendMoneyErrorVisible.value = true
            isValid = false
        } else {
            if (mobile.value?.trim()?.validate("mobile") == false) {
                mobileSendMoneyError.value = "Mobile number is not valid"
                mobileSendMoneyErrorVisible.value = true
                isValid = false
            } else {
                mobileSendMoneyError.value = ""
                mobileSendMoneyErrorVisible.value = false
            }
        }

        if (nameSendMoney.value?.trim().isNullOrBlank()) {
            nameSendMoneyError.value = "Name is required"
            nameSendMoneyErrorVisible.value = true
            isValid = false
        } else {
            if (nameSendMoney.value?.trim()?.validate("name")==false) {
                nameSendMoneyError.value = "Name is not valid"
                nameSendMoneyErrorVisible.value = true
                isValid = false
            } else {
                nameSendMoneyError.value = ""
                nameSendMoneyErrorVisible.value = false
            }
        }

        return isValid
    }


    fun mobileNumberValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true


        if (mobileSendMoney.value?.trim().isNullOrBlank()) {

            isValid = false
        }


        else {

            isValid = mobile.value?.trim()?.length == 10
        }



        return isValid
    }



    fun creditValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true


        if (credit_card.value?.trim().isNullOrBlank()) {
            credit_cardError.value = "Please enter Credit card number"
            credit_cardErrorVisible.value = true
            isValid = false
        } else {
            if (credit_card.value?.trim()?.length!=16){
                credit_cardError.value = "Credit card number is not valid."
                credit_cardErrorVisible.value = true
            }
            else{
                credit_cardError.value = ""
                credit_cardErrorVisible.value = false
            }

        }



        if (credit_holder_name.value?.trim().isNullOrBlank()) {
            credit_holder_name_Error.value = "Name is required"
            credit_holder_name_ErrorVisible.value = true
            isValid = false
        } else {
            if (credit_holder_name.value?.trim()?.validate("name")==false) {
                credit_holder_name_Error.value = "Name is not valid"
                credit_holder_name_ErrorVisible.value = true
                isValid = false
            } else {
                credit_holder_name_Error.value = ""
                credit_holder_name_ErrorVisible.value = false
            }
        }


        if (credit_mobile.value?.trim().isNullOrBlank()) {
            credit_mobile_Error.value = "Mobile number is required"
            credit_mobileErrorVisible.value = true
            isValid = false
        } else {
            if (credit_mobile.value?.trim()?.validate("mobile") == false) {
                credit_mobile_Error.value = "Mobile number is not valid"
                credit_mobileErrorVisible.value = true
                isValid = false
            } else {
                credit_mobile_Error.value = ""
                credit_mobileErrorVisible.value = false
            }
        }

        if (credit_amt.value?.trim().isNullOrBlank()) {
            credit_amt_Error.value = "Please enter amount"
            credit_amt_ErrorVisible.value = true
            isValid = false
        } else {
            credit_amt_Error.value = ""
            credit_amt_ErrorVisible.value = false
        }


        if (credit_remarks.value?.trim().isNullOrBlank()) {
            credit_remarks_Error.value = "Please enter a relevant message."
            credit_remarks_ErrorVisible.value = true
            isValid = false
        } else {
            credit_remarks_Error.value = ""
            credit_remarks_ErrorVisible.value = false
        }
        if(selectedButton.value==0){
            isValid=false
            select_card_type_ErrorVisible.value=true
        }
        else{
            select_card_type_ErrorVisible.value=false
            select_card_type.value=when(selectedButton.value){
               R.id.radioVisa->{"visa"}
               R.id.radioMasterCard->{"mastercard"}
                else -> {"amex"}
            }

        }


        return isValid
    }

    fun beneficiaryValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true


        if (beneficiary_bank_name.value?.trim().isNullOrBlank()) {
            beneficiary_bank_name_Error.value = "This field is required"
            beneficiary_bank_name_ErrorVisible.value = true
            isValid = false
        } else {
            beneficiary_bank_name_Error.value = ""
            beneficiary_bank_name_ErrorVisible.value = false

        }



        if (beneficiary_ifsc.value?.trim().isNullOrBlank()) {
            beneficiary_ifsc_Error.value = "This field is required"
            beneficiary_ifsc_ErrorVisible.value = true
            isValid = false
        } else {
            beneficiary_ifsc_Error.value = ""
            beneficiary_ifsc_ErrorVisible.value = false
        }


        if (beneficiary_acc.value?.trim().isNullOrBlank()) {
            beneficiary_accError.value = "This field is required"
            beneficiary_accErrorVisible.value = true
            isValid = false
        } else {
            beneficiary_accError.value = ""
            beneficiary_accErrorVisible.value = false
        }

        if (beneficiary_name.value?.trim().isNullOrBlank()) {
            beneficiary_nameError.value = "This field is required"
            beneficiary_nameErrorVisible.value = true
            isValid = false
        } else {
            beneficiary_nameError.value = ""
            beneficiary_nameErrorVisible.value = false
        }
        return isValid
    }
    fun beneficiaryVerifyValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true


        if (beneficiary_bank_name.value?.trim().isNullOrBlank()) {
            beneficiary_bank_name_Error.value = "This field is required"
            beneficiary_bank_name_ErrorVisible.value = true
            isValid = false
        } else {
            beneficiary_bank_name_Error.value = ""
            beneficiary_bank_name_ErrorVisible.value = false

        }



        if (beneficiary_ifsc.value?.trim().isNullOrBlank()) {
            beneficiary_ifsc_Error.value = "This field is required"
            beneficiary_ifsc_ErrorVisible.value = true
            isValid = false
        } else {
            beneficiary_ifsc_Error.value = ""
            beneficiary_ifsc_ErrorVisible.value = false
        }


        if (beneficiary_acc.value?.trim().isNullOrBlank()) {
            beneficiary_accError.value = "This field is required"
            beneficiary_accErrorVisible.value = true
            isValid = false
        } else {
            beneficiary_accError.value = ""
            beneficiary_accErrorVisible.value = false
        }


        return isValid
    }

    fun cashWithdrawalValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true


        if (provided_amt.value?.trim().isNullOrBlank()) {
            provided_amt_Error.value = "This field is required"
            provided_amt_ErrorVisible.value = true
            isValid = false
        } else {
            provided_amt_Error.value = ""
            provided_amt_ErrorVisible.value = false

        }



        if (provided_aadhar_number.value?.trim().isNullOrBlank()) {
            provided_aadhar_numberError.value = "Aadhar number is required"
            provided_aadhar_numberErrorVisible.value = true
            isValid = false
        } else {
            if (provided_aadhar_number.value?.trim()?.validate("aadhar")==false) {
                provided_aadhar_numberError.value = "Aadhar number is not valid"
                provided_aadhar_numberErrorVisible.value = true
                isValid = false
            } else {
                provided_aadhar_numberError.value = ""
                provided_aadhar_numberErrorVisible.value = false
            }
        }


        if (provided_customer_number.value?.trim().isNullOrBlank()) {
            provided_customer_numberError.value = "This field is required"
            provided_customer_numberErrorVisible.value = true
            isValid = false
        } else {
            provided_customer_numberError.value = ""
            provided_customer_numberErrorVisible.value = false
        }







        return isValid
    }

    fun fastTagValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (vehicleRegId.value?.trim().isNullOrBlank()) {
            vehicleRegIdError.value = "Registration Number"
            vehicleRegIdErrorVisible.value = true
            isValid = false
        } else {


            if (vehicleRegId.value?.trim()?.validate("vehicle")==false) {
                vehicleRegIdError.value = "Invalid Registration Number"
                vehicleRegIdErrorVisible.value = true
                isValid = false
            } else {
                vehicleRegIdError.value = ""
                vehicleRegIdErrorVisible.value = false
            }
        }

        if (fastTagOperator.value?.trim().isNullOrBlank()) {
            fastTagOperatorError.value = "Please select an operator"
            fastTagOperatorErrorVisible.value = true
            isValid = false
        } else {
            fastTagOperatorError.value = ""
            fastTagOperatorErrorVisible.value = false
        }

        if (fastTagAmt.value?.trim().isNullOrBlank()) {
            fastTagAmtError.value = "Please enter a valid amount."
            fastTagAmtErrorVisible.value = true
            isValid = false
        } else {
            fastTagAmtError.value = ""
            fastTagAmtErrorVisible.value = false
        }
        return isValid
    }

     fun balenceValidation(): Boolean {
         invisibleErrorTexts()

         var isValid = true
         if (balence_enquary_aadhar_number.value?.trim().isNullOrBlank()) {
             balence_enquary_aadhar_numberError.value = "Aadhar number is required"
             balence_enquary_aadhar_numberErrorVisible.value = true
             isValid = false
         } else {
             if (balence_enquary_aadhar_number.value?.trim()?.validate("aadhar")==false) {
                 balence_enquary_aadhar_numberError.value = "Aadhar number is not valid"
                 balence_enquary_aadhar_numberErrorVisible.value = true
                 isValid = false
             } else {
                 balence_enquary_aadhar_numberError.value = ""
                 balence_enquary_aadhar_numberErrorVisible.value = false
             }
         }


         if (balence_enquary_customer_number.value?.trim().isNullOrBlank()) {
             balence_enquary_customer_numberError.value = "This field is required"
             balence_enquary_customer_numberErrorVisible.value = true
             isValid = false
         } else {
             balence_enquary_customer_numberError.value = ""
             balence_enquary_customer_numberErrorVisible.value = false
         }
         return isValid
    }

    fun loanValidation(): Boolean {
        invisibleErrorTexts()

        var isValid = true
        if (selectBank.value?.trim().isNullOrBlank()) {
            selectBankError.value = "Please select bank"
            selectBankErrorVisible.value = true
            isValid = false
        } else {
            selectBankError.value = ""
            selectBankErrorVisible.value = false
        }

        if (loanAccountNumber.value?.trim().isNullOrBlank()) {
            loanAccountNumberError.value = "Please enter loan account number"
            loanAccountNumberErrorVisible.value = true
            isValid = false
        } else {
            loanAccountNumberError.value = ""
            loanAccountNumberErrorVisible.value = false
        }

        if (loanAmount.value?.trim().isNullOrBlank()) {
            loanAmountError.value = "Please enter a loan amount."
            loanAmountErrorVisible.value = true
            isValid = false
        } else {
            loanAmountError.value = ""
            loanAmountErrorVisible.value = false
        }



        return isValid
    }

    fun operatorValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (operatorSubId.value?.trim().isNullOrBlank()) {
            operatorSubIdError.value = "This field is required"
            operatorSubIdErrorVisible.value = true
            isValid = false
        } else {
            operatorSubIdError.value = ""
            operatorSubIdErrorVisible.value = false
        }

        if (selectOperator.value?.trim().isNullOrBlank()) {
            selectOperatorError.value = "Please select an operator"
            selectOperatorErrorVisible.value = true
            isValid = false
        } else {
            selectOperatorError.value = ""
            selectOperatorErrorVisible.value = false
        }

        if (operatordthAmt.value?.trim().isNullOrBlank()) {
            operatordthAmtError.value = "Please enter a valid amount."
            operatordthAmtErrorVisible.value = true
            isValid = false
        } else {
            operatordthAmtError.value = ""
            operatordthAmtErrorVisible.value = false
        }



        return isValid
    }

    fun educationValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (instituteName.value?.trim().isNullOrBlank()) {
            instituteNameError.value = "This field is required"
            instituteNameErrorVisible.value = true
            isValid = false
        } else {
            instituteNameError.value = ""
            instituteNameErrorVisible.value = false
        }

        if (selectInstitute.value?.trim().isNullOrBlank()) {
            selectInstituteError.value = "Please select institute type"
            selectInstituteErrorVisible.value = true
            isValid = false
        } else {
            selectInstituteError.value = ""
            selectInstituteErrorVisible.value = false
        }

        if (education_bank_name.value?.trim().isNullOrBlank()) {
            education_bank_nameError.value = "Select Bank"
            education_bank_nameErrorVisible.value = true
            isValid = false
        } else {
            education_bank_nameError.value = ""
            education_bank_nameErrorVisible.value = false
        }
        if (educationAmt.value?.trim().isNullOrBlank()) {
            educationAmtError.value = "Enter Amount"
            educationAmtErrorVisible.value = true
            isValid = false
        } else {
            educationAmtError.value = ""
            educationAmtErrorVisible.value = false
        }
        return isValid
    }

    fun bookACylinderValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true
        if (gasbookingNumber.value?.trim().isNullOrBlank()) {
            gasbookingNumberError.value = "This field is required"
            gasbookingNumberErrorVisible.value = true
            isValid = false
        } else {
            gasbookingNumberError.value = ""
            gasbookingNumberErrorVisible.value = false
        }

        if (gasBiller.value?.trim().isNullOrBlank()) {
            gasBillerError.value = "Please select institute type"
            gasBillerErrorVisible.value = true
            isValid = false
        } else {
            gasBillerError.value = ""
            gasBillerErrorVisible.value = false
        }


        if (gasBookingAmt.value?.trim().isNullOrBlank()) {
            gasBookingAmtError.value = "Enter Amount"
            gasBookingAmtErrorVisible.value = true
            isValid = false
        } else {
            gasBookingAmtError.value = ""
            gasBookingAmtErrorVisible.value = false
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


        credit_amt_ErrorVisible.value = false
        credit_mobileErrorVisible.value = false
        credit_remarks_ErrorVisible.value = false
        credit_holder_name_ErrorVisible.value = false
        credit_cardErrorVisible.value = false

    }






}