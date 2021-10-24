package com.example.orderbookcompose.data.network.web_socket.dto

data class SocketResponseDto(
    val changes: List<List<String>>,
    val product_id: String,
    val time: String,
    val type: String
)
