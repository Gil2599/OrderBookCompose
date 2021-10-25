package com.example.orderbookcompose.data.network.web_socket.dto

import com.example.orderbookcompose.domain.model.OrderBookUpdate
import java.lang.Exception

data class SocketResponseDto(
    val changes: List<List<String>>? = null,
    val product_id: String? = null,
    val time: String? = null,
    val type: String? = null,
    val exception: Exception? = null
)

fun SocketResponseDto.toOrderBookUpdate(): OrderBookUpdate? {
    return if (type == "l2update") {
        OrderBookUpdate(
            isBuy = changes!![0][0] == "buy",
            price = changes[0][1].toFloat(),
            size = changes[0][2].toFloat(),
            product_id = product_id!!
        )
    }
    else return null
}
