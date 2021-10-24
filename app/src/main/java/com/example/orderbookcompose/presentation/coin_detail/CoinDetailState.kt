package com.example.orderbookcompose.presentation.coin_detail

import com.example.orderbookcompose.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
