package com.example.orderbookcompose.data.repository

import android.util.Log
import com.example.orderbookcompose.data.network.web_socket.CoinbaseWebSocketListener
import com.example.orderbookcompose.data.network.web_socket.WebSocketServiceProvider
import com.example.orderbookcompose.domain.repository.WebSocketRepository
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

class WebSocketRepositoryImpl (
    private val webSocketServiceProvider: WebSocketServiceProvider
): WebSocketRepository {

    override suspend fun startSocket(): Channel<CoinbaseWebSocketListener.SocketUpdate>  {
        return webSocketServiceProvider.startSocket()
    }

    override fun closeSocket() {
        Log.e("Close", "Closing socket...")
        webSocketServiceProvider.stopSocket()
    }
}