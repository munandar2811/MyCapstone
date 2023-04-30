package com.example.core.data.remote.retrofit

import com.example.core.data.remote.respon.LatesCoinRespon
import com.example.core.data.remote.respon.ResponseDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getListCoin(
        @Query("start") start:Int,
        @Query("limit") limit:Int
    ):LatesCoinRespon

    @GET("v2/cryptocurrency/quotes/latest")
    suspend fun getDetailCoin(
        @Query("id") id:Int?=null
    ):ResponseDetail


}