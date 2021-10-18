package com.example.orderbookcompose.domain.repository

import com.example.orderbookcompose.data.network.dto.CoinDetailDto
import com.example.orderbookcompose.data.network.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}