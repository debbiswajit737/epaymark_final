package com.epaymark.epay.data.viewMovel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epaymark.epay.data.genericmodel.BaseResponse
import com.epaymark.epay.data.model.sample.Test
import com.epaymark.epay.network.ResponseState

import com.epaymark.epay.repository.DeliveryOptionsRepository
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: DeliveryOptionsRepository) : ViewModel() {


    val loginResponseLiveData: LiveData<ResponseState<BaseResponse<Test>>>
        get() = repository.loginResponseLiveData
    fun login(requestBody: JsonObject) {
        viewModelScope.launch {
            repository.login(requestBody)
        }
    }

}