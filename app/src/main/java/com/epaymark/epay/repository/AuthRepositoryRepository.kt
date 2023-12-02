package com.epaymark.epay.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.epaymark.epay.data.genericmodel.BaseResponse
import com.epaymark.epay.data.model.sample.Test
import com.epaymark.epay.network.ResponseState
import com.epaymark.epay.network.RetroApi
import javax.inject.Inject

class AuthRepositoryRepository  @Inject constructor(private val api : RetroApi) {
    private val _formResponseLiveData =
        MutableLiveData< ResponseState<BaseResponse<Test>>>()
    val formResponseLiveData: LiveData<ResponseState<BaseResponse<Test>>>
        get() = _formResponseLiveData



    suspend fun formReg(requestBody: String) {
        _formResponseLiveData.postValue(ResponseState.Loading())
        try {

            val response = api.formReg(requestBody)
            _formResponseLiveData.postValue(ResponseState.create(response,"aa"))
            Log.e("TAG_error", "login:OK "+response)

        } catch (throwable: Throwable) {
            _formResponseLiveData.postValue(ResponseState.create(throwable))
            Log.e("TAG_error", "login: "+throwable.message)
        }

    }
}


