package com.epaymark.epay.network

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.intuit.sdp.BuildConfig

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    fun getClient(): Retrofit.Builder{

        //custom interceptor
        val commonHeaderInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }

        //logger
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        //Okhttp Client
        val client = OkHttpClient.Builder().also { client ->
            client.readTimeout(60, TimeUnit.SECONDS)
            client.writeTimeout(60, TimeUnit.SECONDS)
            client.connectTimeout(60, TimeUnit.SECONDS)
            client.addInterceptor(commonHeaderInterceptor)
           // if (BuildConfig.DEBUG) {
                client.addInterceptor(interceptor)
           // }
        }.build()

        val gson = GsonBuilder()
            .setLenient()
            .serializeNulls()
            .setPrettyPrinting()
            .create()

        return Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
    }





}