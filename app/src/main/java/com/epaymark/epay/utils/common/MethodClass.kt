package com.epaymark.epay.utils.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject


object MethodClass {

 fun get_error_method(err: ResponseBody): String {
         val jsonErr = JSONObject(err.string())
         val gson = Gson()
         val type = object : TypeToken<String>() {}.type
         val errorResponse: String? = gson.fromJson(jsonErr.toString(), type)
         return "".toString()
     }

}



