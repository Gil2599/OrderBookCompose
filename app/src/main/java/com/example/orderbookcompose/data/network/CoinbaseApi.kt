package com.example.orderbookcompose.data.network

import com.example.orderbookcompose.data.network.dto.CoinPriceDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinbaseApi {

    @GET("/v2/prices/{coinSymbol}-USD/spot")
    suspend fun getPrice(@Path("coinSymbol") coinSymbol: String): CoinPriceDto
}