package com.epaymark.epay.utils.helpers

import android.content.ContentValues
import android.net.Uri
import androidx.lifecycle.MutableLiveData

object Constants {
    const val INPUT_FILTER_MAX_VALUE:Long = 9999999999999
    const val INPUT_FILTER_POINTER_LENGTH = 2
    const val LOTTIE_CONFETTIE_LINK = "https://assets10.lottiefiles.com/packages/lf20_3xwxlyv7.json"
    const val LOTTIE_TICK_LINK = "https://assets8.lottiefiles.com/packages/lf20_ikemt7or.json"
    const val CHIPER_CODE = "AES/CBC/PKCS5Padding"
    const val AES_CODE = "PBKDF2WithHmacSHA256"
    const val SECRET_KEY = "AES"
    const val EPAY_SHAREDFREFFRENCE = "EPAY_SHAREDFREFFRENCE"
    const val TEST = "TEST"
    var isDthOperator=false
    var isVideo=false
    var isIsPaySlip=false
    var isPdf=false
    var isBackCamera=true
    var isGallary=false
    var contentValues: ContentValues? =null

}