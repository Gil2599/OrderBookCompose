package com.example.orderbookcompose.domain.repository

import com.example.orderbookcompose.data.network.dto.CoinDetailDto
import com.example.orderbookcompose.data.network.dto.CoinDto
import com.example.orderbookcompose.data.network.dto.CoinPriceDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

    suspend fun getPrice(coinSymbol: String): CoinPriceDto

}