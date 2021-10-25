package com.example.orderbookcompose.domain.model

data class OrderBookUpdate(
    val isBuy: Boolean,
    val price: Float,
    val size: Float,
    val product_id: String
)
