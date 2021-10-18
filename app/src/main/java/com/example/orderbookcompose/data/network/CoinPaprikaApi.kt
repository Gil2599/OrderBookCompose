package com.example.orderbookcompose.data.network

import com.example.orderbookcompose.data.network.dto.CoinDetailDto
import com.example.orderbookcompose.data.network.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

}