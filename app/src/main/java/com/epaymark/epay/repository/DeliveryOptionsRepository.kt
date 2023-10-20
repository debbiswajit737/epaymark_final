package com.epaymark.epay.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.epaymark.epay.data.model.sample.Test
import com.epaymark.epay.network.ResponseState
import com.epaymark.epay.network.RetroApi
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class DeliveryOptionsRepository  @Inject constructor(private val api : RetroApi) {

    private val _loginResponseLiveData =
        MutableLiveData< ResponseState<Test>>()
    val loginResponseLiveData: LiveData<ResponseState<Test>>
        get() = _loginResponseLiveData



    suspend fun login(requestBody: JsonObject) {
        _loginResponseLiveData.postValue(ResponseState.Loading())
        try {

            val response = api.login(requestBody)
            _loginResponseLiveData.postValue(ResponseState.create(response,"aa"))
            Log.e("TAG_error", "login:OK "+response)

        } catch (throwable: Throwable) {
            _loginResponseLiveData.postValue(ResponseState.create(throwable))
            Log.e("TAG_error", "login: "+throwable.message)
        }

    }
}


