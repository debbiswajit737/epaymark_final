package com.epaymark.epay.data.viewMovel

import android.net.Uri
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epaymark.epay.data.model.FileModel

import com.epaymark.epay.repository.DeliveryOptionsRepository
import com.epaymark.epay.utils.helpers.helper.validate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: DeliveryOptionsRepository) : ViewModel() {
    var lastView: View?=null
    var filePath: MutableLiveData<Uri> = MutableLiveData()
    var videoFilePath: MutableLiveData<Uri> = MutableLiveData()
    var keyPadValue = MutableLiveData("")
    var mobError = MutableLiveData("")
    var timingValue = MutableLiveData("")
    //var otp = MutableLiveData(Editable)
    val otp: MutableLiveData<String> = MutableLiveData("")


    val videoFile = MutableLiveData<FileModel>()


    // Fields
    val name = MutableLiveData<String>()
    val mobile = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val alternativeMobile = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val pinCode = MutableLiveData<String>()
    val dateOfBirth = MutableLiveData<String>()
    val state = MutableLiveData<String>()
    val city = MutableLiveData<String>()
    val area = MutableLiveData<String>()
    val gender = MutableLiveData<String>()
    val aadhar = MutableLiveData<String>()
    val panCardNo = MutableLiveData<String>()
    val panCardImage = MutableLiveData<String>()
    val aadharFontImage = MutableLiveData<String>()
    val aadharbackImage = MutableLiveData<String>()
    val businessType = MutableLiveData<String>()
    val businessCategory = MutableLiveData<String>()
    val businessName = MutableLiveData<String>()
    val businessAddress = MutableLiveData<String>()
    val beneficiaryName = MutableLiveData<String>()
    val accountNumber = MutableLiveData<String>()
    val confirmAccountNumber = MutableLiveData<String>()
    val bankName = MutableLiveData<String>()
    val ifscCode = MutableLiveData<String>()
    val employeeCode = MutableLiveData<String>()
    val panPath = MutableLiveData<String>()
    val cpanPath = MutableLiveData<String>()
    val paadhar = MutableLiveData<String>()
    val PartnerAadharBack = MutableLiveData<String>()
    val llGst = MutableLiveData<String>()
    val llCertificateOfIncorporation = MutableLiveData<String>()
    val llBoardResolution = MutableLiveData<String>()
    val llTrade = MutableLiveData<String>()
    val llUserSelfi = MutableLiveData<String>()
    val llCselfi = MutableLiveData<String>()
    val videokyc = MutableLiveData<String>()

    val llPan = MutableLiveData<String>()
    val llCpan = MutableLiveData<String>()
    val llBpan = MutableLiveData<String>()


    val genderReg = MutableLiveData<String>()

    val llPanBase64 = MutableLiveData<String>()
    val llCpanBase64 = MutableLiveData<String>()
    val llBpanBase64 = MutableLiveData<String>()


    val pancardImage3 = MutableLiveData<String>()
    val cancleCheck = MutableLiveData<String>()



    // Error messages for fields
    val nameError = MutableLiveData<String>()
    val mobileError = MutableLiveData<String>()
    val emailError = MutableLiveData<String>()
    val alternativeMobileError = MutableLiveData<String>()
    val addressError = MutableLiveData<String>()
    val pinCodeError = MutableLiveData<String>()
    val dateOfBirthError = MutableLiveData<String>()
    val stateError = MutableLiveData<String>()
    val cityError = MutableLiveData<String>()
    val areaError = MutableLiveData<String>()
    val genderError = MutableLiveData<String>()
    val aadharError = MutableLiveData<String>()
    val panCardNoError = MutableLiveData<String>()
    val panCardImageError = MutableLiveData<String>()
    val aadharFontError = MutableLiveData<String>()
    val aadharbackImageError = MutableLiveData<String>()
    val businessTypeError = MutableLiveData<String>()
    val businessCategoryError = MutableLiveData<String>()
    val businessNameError = MutableLiveData<String>()
    val chooseCancleCheckError = MutableLiveData<String>()
    val businessAddressError = MutableLiveData<String>()
    val beneficiaryNameError = MutableLiveData<String>()
    val accountNumberError = MutableLiveData<String>()
    val confirmAccountNumberError = MutableLiveData<String>()
    val bankNameError = MutableLiveData<String>()
    val ifscCodeError = MutableLiveData<String>()
    val employeeCodeError = MutableLiveData<String>()

    // Error visibility for fields
    val nameErrorVisible = MutableLiveData<Boolean>()
    val mobileErrorVisible = MutableLiveData<Boolean>()
    val emailErrorVisible = MutableLiveData<Boolean>()
    val alternativeMobileErrorVisible = MutableLiveData<Boolean>()
    val addressErrorVisible = MutableLiveData<Boolean>()
    val pinCodeErrorVisible = MutableLiveData<Boolean>()
    val dateOfBirthErrorVisible = MutableLiveData<Boolean>()
    val stateErrorVisible = MutableLiveData<Boolean>()
    val cityErrorVisible = MutableLiveData<Boolean>()
    val areaErrorVisible = MutableLiveData<Boolean>()
    val genderErrorVisible = MutableLiveData<Boolean>()
    val aadharErrorVisible = MutableLiveData<Boolean>()
    val panCardNoErrorVisible = MutableLiveData<Boolean>()
    val panCardImageErrorVisible = MutableLiveData<Boolean>()
    val aadharFontErrorVisible = MutableLiveData<Boolean>()
    val aadharbackImageErrorVisible = MutableLiveData<Boolean>()
    val businessTypeErrorVisible = MutableLiveData<Boolean>()
    val businessCategoryErrorVisible = MutableLiveData<Boolean>()
    val businessNameErrorVisible = MutableLiveData<Boolean>()
    val businessAddressErrorVisible = MutableLiveData<Boolean>()
    val beneficiaryNameErrorVisible = MutableLiveData<Boolean>()
    val accountNumberErrorVisible = MutableLiveData<Boolean>()
    val confirmAccountNumberErrorVisible = MutableLiveData<Boolean>()
    val bankNameErrorVisible = MutableLiveData<Boolean>()
    val ifscCodeErrorVisible = MutableLiveData<Boolean>()
    val employeeCodeErrorVisible = MutableLiveData<Boolean>()
    val chooseCancleCheckErrorVisible = MutableLiveData<Boolean>()



    val panPathErrorVisible = MutableLiveData<Boolean>()
    val cpanPathErrorVisible = MutableLiveData<Boolean>()
    val paadharErrorVisible = MutableLiveData<Boolean>()
    val PartnerAadharBackErrorVisible = MutableLiveData<Boolean>()
    val llGstErrorVisible = MutableLiveData<Boolean>()
    val llCertificateOfIncorporationErrorVisible = MutableLiveData<Boolean>()
    val llBoardResolutionErrorVisible = MutableLiveData<Boolean>()
    val llTradeErrorVisible = MutableLiveData<Boolean>()
    val llUserSelfiErrorVisible = MutableLiveData<Boolean>()
    val llCselfiErrorVisible = MutableLiveData<Boolean>()
    val videokycErrorVisible = MutableLiveData<Boolean>()

    val llPanErrorVisible = MutableLiveData<Boolean>()
    val llCpanErrorVisible = MutableLiveData<Boolean>()
    val llBpanErrorVisible = MutableLiveData<Boolean>()

    //validation
    fun regValidation(): Boolean {

        invisibleErrorTexts()

        var isValid = true

        if (name.value?.trim().isNullOrBlank()) {
            nameError.value = "Name is required"
            nameErrorVisible.value = true
            isValid = false
        } else {
            if (name.value?.trim()?.validate("name")==false) {
                nameError.value = "Name is not valid"
                nameErrorVisible.value = true
                isValid = false
            } else {
                nameError.value = ""
                nameErrorVisible.value = false
            }
        }





        if (mobile.value?.trim().isNullOrBlank()) {
            mobileError.value = "Mobile number is required"
            mobileErrorVisible.value = true
            isValid = false
        } else {
            if (mobile.value?.trim()?.validate("mobile")==false) {
                mobileError.value = "Mobile number is not valid"
                mobileErrorVisible.value = true
                isValid = false
            } else {
                mobileError.value = ""
                mobileErrorVisible.value = false
            }
        }





        if (email.value?.trim().isNullOrBlank()) {
            emailError.value = "Email is required"
            emailErrorVisible.value = true
            isValid = false
        } else {
            if (email.value?.trim()?.validate("email")==false){
                emailError.value = "Email is not valid"
                emailErrorVisible.value = true
                isValid = false
            } else {
                emailError.value = ""
                emailErrorVisible.value = false
            }
        }



        if (alternativeMobile.value?.trim().isNullOrBlank()) {
            alternativeMobileError.value = "Alternative mobile number is required"
            alternativeMobileErrorVisible.value = true
            isValid = false
        } else {
            if (alternativeMobile.value?.validate("mobile")==false) {
                alternativeMobileError.value = "Valid alternative mobile number is required"
                alternativeMobileErrorVisible.value = true
                isValid = false
            } else {
                alternativeMobileError.value = ""
                alternativeMobileErrorVisible.value = false
            }
        }





        if (address.value?.trim().isNullOrBlank()) {
            addressError.value = "Address is required"
            addressErrorVisible.value = true
            isValid = false
        } else {
            addressError.value = ""
            addressErrorVisible.value = false
        }

        if (pinCode.value?.trim().isNullOrBlank()) {
            pinCodeError.value = "Pin code is required"
            pinCodeErrorVisible.value = true
            isValid = false
        } else {
            if (pinCode.value?.trim()?.validate("pincode")==false) {
                pinCodeError.value = "PIN code is not valid"
                pinCodeErrorVisible.value = true
                isValid = false
            } else {
                pinCodeError.value = ""
                pinCodeErrorVisible.value = false
            }
        }





        if (dateOfBirth.value?.trim().isNullOrBlank()) {
            dateOfBirthError.value = "Date of birth is required"
            dateOfBirthErrorVisible.value = true
            isValid = false
        } else {
            dateOfBirthError.value = ""
            dateOfBirthErrorVisible.value = false
        }

        if (state.value?.trim().isNullOrBlank()) {
            stateError.value = "State is required"
            stateErrorVisible.value = true
            isValid = false
        } else {
            stateError.value = ""
            stateErrorVisible.value = false
        }

        if (city.value?.trim().isNullOrBlank()) {
            cityError.value = "City is required"
            cityErrorVisible.value = true
            isValid = false
        } else {
            cityError.value = ""
            cityErrorVisible.value = false
        }

        if (area.value?.trim().isNullOrBlank()) {
            areaError.value = "Area is required"
            areaErrorVisible.value = true
            isValid = false
        } else {
            areaError.value = ""
            areaErrorVisible.value = false
        }





        /*if (gender.value?.trim().isNullOrBlank()) {
            genderError.value = "Gender is required"
            genderErrorVisible.value = true
            isValid = false
        } else {
            genderError.value = ""
            genderErrorVisible.value = false
        }*/

        if (aadhar.value?.trim().isNullOrBlank()) {
            aadharError.value = "Aadhar number is required"
            aadharErrorVisible.value = true
            isValid = false
        } else {
            if (aadhar.value?.trim()?.validate("aadhar")==false) {
                aadharError.value = "Aadhar number is not valid"
                aadharErrorVisible.value = true
                isValid = false
            } else {
                aadharError.value = ""
                aadharErrorVisible.value = false
            }
        }





        if (panCardNo.value?.trim().isNullOrBlank()) {
            panCardNoError.value = "Pan card number is required"
            panCardNoErrorVisible.value = true
            isValid = false
        } else {
            if (panCardNo.value?.trim()?.validate("pan")==false) {
                panCardNoError.value = "Pan card number is required"
                panCardNoErrorVisible.value = true
                isValid = false
            } else {
                panCardNoError.value = ""
                panCardNoErrorVisible.value = false
            }
        }


        if (llPan.value?.trim().isNullOrBlank()) {

            panCardNoErrorVisible.value = true
            isValid = false
        } else {
            panCardNoErrorVisible.value = false
        }
        if (llCpan.value?.trim().isNullOrBlank()) {

            aadharFontErrorVisible.value = true
            isValid = false
        } else {
            aadharFontErrorVisible.value = false
        }
        if (llBpan.value?.trim().isNullOrBlank()) {

            aadharbackImageErrorVisible.value = true
            isValid = false
        } else {
            aadharbackImageErrorVisible.value = false
        }
        return isValid

    }

    fun kycValidation(): Boolean {
        var isValid = true
        invisibleErrorTexts()
        if (businessType.value?.trim()=="Select Business Type") {
            businessTypeError.value = "Select Business Type"
            businessTypeErrorVisible.value = true
            isValid = false
        }
        else {
            businessTypeError.value = ""
            businessTypeErrorVisible.value = false
        }

        if (businessCategory.value?.trim()=="Select Business Category") {
            businessCategoryError.value = "Select Business Category"
            businessCategoryErrorVisible.value = true
            isValid = false
        }
        else {
            businessCategoryError.value = ""
            businessCategoryErrorVisible.value = false
        }

        if (businessName.value?.trim().isNullOrBlank()) {
            businessNameError.value = "Please enter a valid business name."
            businessNameErrorVisible.value = true
            isValid = false
        }else {
            businessCategoryError.value = ""
            businessCategoryErrorVisible.value = false
        }

        if (businessAddress.value?.trim().isNullOrBlank()) {
            businessAddressError.value = "Please enter a valid business address."
            businessAddressErrorVisible.value = true
            isValid = false
        }else {
            businessAddressError.value = ""
            businessAddressErrorVisible.value = false
        }




        return isValid
    }


    fun bankDetailsValidation(): Boolean {
        var isValid = true
        invisibleErrorTexts()

        if (beneficiaryName.value?.trim().isNullOrBlank()) {
            beneficiaryNameError.value = "Beneficiary Name is required"
            beneficiaryNameErrorVisible.value = true
            isValid = false
        } else {
            beneficiaryNameError.value = ""
            beneficiaryNameErrorVisible.value = false
        }







        if (accountNumber.value?.trim().isNullOrBlank()) {
            accountNumberError.value = "Account Number is required"
            accountNumberErrorVisible.value = true
            isValid = false
        } else {
            accountNumberError.value = ""
            accountNumberErrorVisible.value = false
        }

        if (confirmAccountNumber.value?.trim().isNullOrBlank()) {
            confirmAccountNumberError.value = "Confirm Account Number is required"
            confirmAccountNumberErrorVisible.value = true
            isValid = false
        } else if (confirmAccountNumber.value != accountNumber.value) {
            confirmAccountNumberError.value = "Account Numbers do not match"
            confirmAccountNumberErrorVisible.value = true
            isValid = false
        } else {
            confirmAccountNumberError.value = ""
            confirmAccountNumberErrorVisible.value = false
        }

        if (bankName.value?.trim()=="Select Bank") {
           // bankNameError.value = "Bank Name is required"
          //  bankNameErrorVisible.value = true
            isValid = false
        } else {
           // bankNameError.value = ""
          //  bankNameErrorVisible.value = false
        }

        if (ifscCode.value?.trim().isNullOrBlank()) {
            ifscCodeError.value = "IFSC Code is required"
            ifscCodeErrorVisible.value = true
            isValid = false
        } else {
            ifscCodeError.value = ""
            ifscCodeErrorVisible.value = false
        }

        if (employeeCode.value?.trim().isNullOrBlank()) {
            employeeCodeError.value = "Employee Code/ID is required"
            employeeCodeErrorVisible.value = true
            isValid = false
        } else {
            employeeCodeError.value = ""
            employeeCodeErrorVisible.value = false
        }

        if (cancleCheck?.value?.trim().isNullOrBlank()) {
            chooseCancleCheckError.value = "Employee Code/ID is required"
            chooseCancleCheckErrorVisible.value = true
            isValid = false
        } else {
            chooseCancleCheckError.value = ""
            chooseCancleCheckErrorVisible.value = false
        }



        // Validate the Cancelled Cheque field for JPG only
        /*val allowedExtensions = listOf("jpg", "jpeg")
        val chequeFilePath = cancelledChequeFilePath // Replace with your LiveData variable for the file path
        if (chequeFilePath.isNullOrBlank()) {
            chequeError.value = "Cancelled Cheque (JPG) is required"
            chequeErrorVisible.value = true
            isValid = false
        } else {
            val fileExtension = chequeFilePath.substringAfterLast('.').toLowerCase()
            if (!allowedExtensions.contains(fileExtension)) {
                chequeError.value = "Invalid file format. JPG files only."
                chequeErrorVisible.value = true
                isValid = false
            } else {
                chequeError.value = ""
                chequeErrorVisible.value = false
            }
        }*/

        return isValid
    }

    /*fun documentValidation(): Boolean {
        var isValid = true
        invisibleErrorTexts()

        val panPath = MutableLiveData<String>()
        val cpanPath = MutableLiveData<String>()
        val paadhar = MutableLiveData<String>()
        val PartnerAadharBack = MutableLiveData<String>()
        val llGst = MutableLiveData<String>()
        val llCertificateOfIncorporation = MutableLiveData<String>()
        val llBoardResolution = MutableLiveData<String>()
        val llTrade = MutableLiveData<String>()
        val llUserSelfi = MutableLiveData<String>()
        val llCselfi = MutableLiveData<String>()
        val videokyc = MutableLiveData<String>()


        if (panPath.value?.trim().isNullOrBlank()) {
            businessNameError.value = "Please enter a valid business name."
            businessNameErrorVisible.value = true
            isValid = false
        }else {
            businessCategoryError.value = ""
            businessCategoryErrorVisible.value = false
        }

    }*/

    fun docValidation(): Boolean {
        var isValid = true
        invisibleErrorTexts()
       if (videokyc.value?.trim().isNullOrBlank()) {
           videokycErrorVisible.value =true
           isValid= false
       }
        else{
           videokycErrorVisible.value =false
       }
        if (llCselfi.value?.trim().isNullOrBlank()) {
            llCselfiErrorVisible.value = true
            isValid= false
       }
        else{
            llCselfiErrorVisible.value = false
        }
         if (llUserSelfi.value?.trim().isNullOrBlank()) {
             llUserSelfiErrorVisible.value = true
             isValid= false
       }
        else{
             llUserSelfiErrorVisible.value=false
         }




        return isValid
    }
    //error texts primary state
    fun invisibleErrorTexts() {
         nameErrorVisible.value = false
         mobileErrorVisible.value = false
         emailErrorVisible.value = false
         alternativeMobileErrorVisible.value = false
         addressErrorVisible.value = false
         pinCodeErrorVisible.value = false
         dateOfBirthErrorVisible.value = false
         stateErrorVisible.value = false
         cityErrorVisible.value = false
         areaErrorVisible.value = false
         genderErrorVisible.value = false
         aadharErrorVisible.value = false
         panCardNoErrorVisible.value = false
         panCardImageErrorVisible.value = false
         aadharFontErrorVisible.value = false
         aadharbackImageErrorVisible.value = false


        businessCategoryErrorVisible.value = false
        businessNameErrorVisible.value = false
        businessAddressErrorVisible.value = false

    }
}