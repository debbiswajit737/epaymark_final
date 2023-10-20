package com.epaymark.epay.di

import com.epaymark.epay.network.RetroApi
import com.epaymark.epay.network.RetrofitHelper

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit.Builder {
        return RetrofitHelper.getClient()
    }


    @Singleton
    @Provides
    fun providesApiService(retrofitBuilder: Retrofit.Builder): RetroApi {
        //without token
        return retrofitBuilder.build().create(RetroApi::class.java)
    }


}