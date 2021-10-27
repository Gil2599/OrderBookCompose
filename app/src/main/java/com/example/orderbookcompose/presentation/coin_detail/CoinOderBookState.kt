package com.example.orderbookcompose.presentation.coin_detail

import com.example.orderbookcompose.domain.model.CoinDetail
import java.util.*

data class CoinOderBookState(
    val isLoading: Boolean = false,
    val orderBook: List<Pair<Float, Float>>? = null,
    val error: String = ""
)