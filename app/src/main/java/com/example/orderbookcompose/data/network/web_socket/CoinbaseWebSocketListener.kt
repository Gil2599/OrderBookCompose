package com.example.orderbookcompose.data.network.web_socket

import android.util.Log
import com.example.orderbookcompose.data.network.web_socket.dto.SocketResponseDto
import com.example.orderbookcompose.data.network.web_socket.dto.SocketSubscribeDto
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class CoinbaseWebSocketListener (marketRequest: SocketSubscribeDto): WebSocketListener() {
    private var requestMarket: SocketSubscribeDto = marketRequest

    val socketEventChannel: Channel<SocketResponseDto> = Channel()
    private val GSON = GsonBuilder().setPrettyPrinting().create()

    override fun onOpen(webSocket: WebSocket, response: Response) {
        webSocket.send(toJSON())
    }

    override fun onMessage(webSocket: WebSocket, text: String) {

        //Log.e("Test", SocketUpdate(text).text.toString())
        CoroutineScope(Dispatchers.Default).launch {
            try {
                //Log.e("Test", text)
                socketEventChannel.send(Gson().fromJson(text, SocketResponseDto::class.java))
            }
            catch (ex: java.lang.Exception){

                socketEventChannel.send(SocketResponseDto(exception = ex))

            }
        }
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Log.e("Testing", "onClosing function")

        webSocket.cancel()
        socketEventChannel.close()
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Log.e("Error", "onFailure")
    }

    class SocketAbortedException : Exception()


    private fun toJSON(): String {

        val fullRequest = GSON.toJson(requestMarket, requestMarket.javaClass)

        //Log.e("Test", fullRequest)

        return fullRequest
    }

    companion object {
        const val NORMAL_CLOSURE_STATUS = 1000
    }
}