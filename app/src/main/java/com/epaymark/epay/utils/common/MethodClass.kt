package com.epaymark.epay.utils.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
    fun check_networkconnection(ctx: Context?): Boolean {
        var isConnected = false
        ctx?.let {
            val connectivityManager: ConnectivityManager? =
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetwork = connectivityManager?.activeNetwork ?: return false
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            isConnected = when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }

        return isConnected

    }
}



