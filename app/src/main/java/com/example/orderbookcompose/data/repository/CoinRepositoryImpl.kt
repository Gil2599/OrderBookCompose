package com.example.orderbookcompose.data.repository

import com.example.orderbookcompose.data.network.CoinPaprikaApi
import com.example.orderbookcompose.data.network.dto.CoinDetailDto
import com.example.orderbookcompose.data.network.dto.CoinDto
import com.example.orderbookcompose.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}