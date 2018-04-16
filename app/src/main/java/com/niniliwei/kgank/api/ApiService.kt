package com.niniliwei.kgank.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object ApiUrl {
        const val WELFARE = "data/福利/{pageSize}/{pageNo}"
    }

    @GET(WELFARE)
    fun getWelfareList(@Path("pageSize") pageSize: Int,
                       @Path("pageNo") pageNo: Int): Single<BaseResp>
}