package com.niniliwei.kgank.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitManager {

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://gank.io/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }
}