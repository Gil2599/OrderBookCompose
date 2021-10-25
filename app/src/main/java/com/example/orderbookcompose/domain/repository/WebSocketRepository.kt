package com.example.orderbookcompose.domain.repository

import com.example.orderbookcompose.data.network.web_socket.CoinbaseWebSocketListener
import com.example.orderbookcompose.data.network.web_socket.dto.SocketResponseDto
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

interface WebSocketRepository {

    suspend fun startSocket(): Channel<SocketResponseDto>

    fun closeSocket()
}