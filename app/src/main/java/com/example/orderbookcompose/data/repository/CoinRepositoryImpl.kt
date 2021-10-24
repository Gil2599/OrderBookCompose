package com.example.orderbookcompose.data.repository

import com.example.orderbookcompose.data.network.CoinPaprikaApi
import com.example.orderbookcompose.data.network.CoinbaseApi
import com.example.orderbookcompose.data.network.dto.CoinDetailDto
import com.example.orderbookcompose.data.network.dto.CoinDto
import com.example.orderbookcompose.data.network.dto.CoinPriceDto
import com.example.orderbookcompose.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val paprikaApi: CoinPaprikaApi,
    private val coinbaseApi: CoinbaseApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return paprikaApi.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return paprikaApi.getCoinById(coinId)
    }

    override suspend fun getPrice(coinSymbol: String): CoinPriceDto{
        return coinbaseApi.getPrice(coinSymbol)
    }

}