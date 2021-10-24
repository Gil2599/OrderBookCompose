package com.example.orderbookcompose.domain.repository

import com.example.orderbookcompose.data.network.web_socket.CoinbaseWebSocketListener
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

interface WebSocketRepository {

    suspend fun startSocket(): Channel<CoinbaseWebSocketListener.SocketUpdate>

    fun closeSocket()
}