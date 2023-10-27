package com.epaymark.epay.data.viewMovel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epaymark.epay.R
import com.epaymark.epay.data.genericmodel.BaseResponse
import com.epaymark.epay.data.model.sample.Test
import com.epaymark.epay.network.ResponseState

import com.epaymark.epay.repository.DeliveryOptionsRepository
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: DeliveryOptionsRepository) : ViewModel() {

    var keyPadValue = MutableLiveData("")
    var mobError = MutableLiveData("")
    var timingValue = MutableLiveData("")
    //var otp = MutableLiveData(Editable)
    val otp: MutableLiveData<String> = MutableLiveData("")
}