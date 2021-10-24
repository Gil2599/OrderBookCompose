package com.example.orderbookcompose.data.network.web_socket.dto

data class SocketSubscribeDto(
    val channels: List<Any>,
    val product_ids: List<String>,
    val type: String = "subscribe"
)

