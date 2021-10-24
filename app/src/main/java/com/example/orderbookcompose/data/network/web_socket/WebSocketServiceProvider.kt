package com.example.orderbookcompose.data.network.web_socket

import android.util.Log
import com.example.orderbookcompose.common.Constants
import com.example.orderbookcompose.data.network.web_socket.dto.SocketSubscribeDto
import kotlinx.coroutines.channels.Channel
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import javax.inject.Inject

class WebSocketServiceProvider (coinbaseWebSocketListener: CoinbaseWebSocketListener) {
    private var _webSocket: WebSocket? = null

    private val client = OkHttpClient()
    private val request: Request = Request.Builder().url(Constants.SOCKET_BASE_URL).build()

    private var _webSocketListener = coinbaseWebSocketListener

    fun startSocket(): Channel<CoinbaseWebSocketListener.SocketUpdate> =

        with(_webSocketListener) {

            startSocket(this)
            this@with.socketEventChannel
        }

    private fun startSocket(webSocketListener: CoinbaseWebSocketListener) {

        _webSocket = client.newWebSocket(request, _webSocketListener)
        client.dispatcher.executorService.shutdown()
    }

    fun stopSocket() {
        try {
            Log.e("Exception", "Trying to close socket")
            _webSocket?.close(NORMAL_CLOSURE_STATUS, null)
            _webSocket = null
            _webSocketListener.socketEventChannel.close()
        } catch (ex: Exception) {
            Log.e("Exception", ex.toString())
        }
    }

    companion object {
        const val NORMAL_CLOSURE_STATUS = 1000
    }

}