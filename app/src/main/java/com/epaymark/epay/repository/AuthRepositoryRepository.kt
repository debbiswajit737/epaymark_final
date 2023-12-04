package com.epaymark.epay.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.epaymark.epay.data.genericmodel.BaseResponse
import com.epaymark.epay.data.model.onBoading.DocumentUploadModel
import com.epaymark.epay.data.model.onBoading.RegForm
import com.epaymark.epay.data.model.sample.Test
import com.epaymark.epay.network.ResponseState
import com.epaymark.epay.network.RetroApi
import javax.inject.Inject

class AuthRepositoryRepository  @Inject constructor(private val api : RetroApi) {
    private val _formResponseLiveData =
        MutableLiveData< ResponseState<BaseResponse<Test>>>()
    val formResponseLiveData: LiveData<ResponseState<BaseResponse<Test>>>
        get() = _formResponseLiveData



    suspend fun formReg(requestBody: RegForm) {
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


    //Doc upload

    private val _docUploadResponseLiveData =
            MutableLiveData< ResponseState<BaseResponse<Test>>>()
        val docUploadResponseLiveData: LiveData<ResponseState<BaseResponse<Test>>>
            get() = _docUploadResponseLiveData



        suspend fun docUpload(requestBody: DocumentUploadModel) {
            _docUploadResponseLiveData.postValue(ResponseState.Loading())
            try {

                val response = api.docUpload(requestBody)
                _docUploadResponseLiveData.postValue(ResponseState.create(response,"aa"))
                //Log.e("TAG_error", "login:OK "+response)

            } catch (throwable: Throwable) {
                _docUploadResponseLiveData.postValue(ResponseState.create(throwable))
                //Log.e("TAG_error", "login: "+throwable.message)
            }

        }


    //Login Model

    private val _loginResponseLiveData =
            MutableLiveData< ResponseState<BaseResponse<Test>>>()
        val loginResponseLiveData: LiveData<ResponseState<BaseResponse<Test>>>
            get() = _loginResponseLiveData



        suspend fun userLogin(loginModel: String) {
            _loginResponseLiveData.postValue(ResponseState.Loading())
            try {

                val response = api.epayLogin(loginModel)
                _loginResponseLiveData.postValue(ResponseState.create(response,"aa"))
                //Log.e("TAG_error", "login:OK "+response)

            } catch (throwable: Throwable) {
                _loginResponseLiveData.postValue(ResponseState.create(throwable))
                //Log.e("TAG_error", "login: "+throwable.message)
            }

        }



}


