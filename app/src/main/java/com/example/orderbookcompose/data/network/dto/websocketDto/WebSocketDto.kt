package com.example.orderbookcompose.data.network.dto.websocketDto

data class SubscribeAction(
    val channels: List<TickerRequest>,
    val product_ids: List<String>,
    val type: String = "subscribe"
)

data class TickerRequest(
    val name: String = "ticker",
    val product_ids: List<String>
)
data class TickerResponse(
    val price: Double
)