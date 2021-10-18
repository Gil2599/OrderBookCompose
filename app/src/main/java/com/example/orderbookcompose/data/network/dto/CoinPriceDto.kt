package com.example.orderbookcompose.data.network.dto

import com.example.orderbookcompose.domain.model.CoinDetail

data class CoinPriceDto(
    val data: Data
)

fun CoinPriceDto.updateCoinPrice(coin: CoinDetail){
    coin.price = data.amount.toFloat()
}