package com.example.orderbookcompose.domain.repository

import com.example.orderbookcompose.data.network.dto.websocketDto.SubscribeAction
import com.example.orderbookcompose.data.network.dto.websocketDto.TickerResponse
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface SocketService {
    @Send
    fun subscribe(action: SubscribeAction)

    @Receive
    fun observeTicker(): Flowable<TickerResponse>

    @Receive
    fun observeWebSocketEvent(): Flowable<WebSocket.Event>
}