package com.example.orderbookcompose.common

import com.example.orderbookcompose.data.network.web_socket.dto.SocketSubscribeDto

object Constants {

    const val PAPRIKA_BASE_URL = "https://api.coinpaprika.com/"

    const val COINBASE_BASE_URL = "https://api.coinbase.com/"

    const val SOCKET_BASE_URL = "wss://ws-feed.pro.coinbase.com"

    const val PARAM_COIN_ID = "coinId"

    val UNSUBSCRIBE = SocketSubscribeDto(
        listOf(""),
        listOf(""),
        "unsubscribe"
    )

}