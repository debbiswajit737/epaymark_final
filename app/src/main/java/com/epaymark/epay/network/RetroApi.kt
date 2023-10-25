package com.epaymark.epay.network

import com.epaymark.epay.data.model.sample.Test
import com.epaymark.epay.data.genericmodel.BaseResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetroApi {

    /*{ "response": { "data": [ { "name":"Test User 1" }, { "name":"Test User 2" } ], "status": { "msg": "Sample message.", "action_status": false }, "publish": { "version": "Api.0.0.0", "developer": "bdas" } } }*/
    @POST("abcd.php")
    suspend fun login(@Body loginRequest: JsonObject): Response<BaseResponse<Test>>//ResponseState<Test>
}