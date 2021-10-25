package com.example.orderbookcompose.domain.use_case.get_orderbook

import android.util.Log
import com.example.orderbookcompose.common.Resource
import com.example.orderbookcompose.data.network.web_socket.CoinbaseWebSocketListener
import com.example.orderbookcompose.data.network.web_socket.dto.SocketResponseDto
import com.example.orderbookcompose.data.network.web_socket.dto.toOrderBookUpdate
import com.example.orderbookcompose.domain.model.CoinDetail
import com.example.orderbookcompose.domain.model.OrderBookUpdate
import com.example.orderbookcompose.domain.repository.WebSocketRepository
import com.google.gson.Gson
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject

class GetOrderBookUseCase (
    webSocketRepository: WebSocketRepository
) {
    private val repository = webSocketRepository

    private val asksMap = sortedMapOf<Float, Float>()
    lateinit var update: OrderBookUpdate

    operator fun invoke() : Flow<Resource<SortedMap<Float, Float>>> = flow {

        try {

            emit(Resource.Loading())

            repository.startSocket().consumeEach {

                if (it.exception == null) {
                    if (it.toOrderBookUpdate() != null) {
                        update = it.toOrderBookUpdate()!!

                        if (update.isBuy){

                        }else putAsksMap(update = update)


                        emit(Resource.Success(asksMap))
                    }


                } else {
                    Log.e("ERROR", "Calling closeSocket...")
                    repository.closeSocket()
                    onSocketError(it.exception)

                }
            }


        } catch (e: HttpException){
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred")
            )
        }

    }


    private fun putAsksMap(update: OrderBookUpdate){

        if (update.size > 0F){
            if (asksMap.size < 11)
                asksMap[update.price] = update.size

            else {
                asksMap[update.price] = update.size
                asksMap.remove(asksMap.lastKey())
            }

        } else asksMap.remove(update.price)


        Log.e("Test", asksMap.toString())
    }


    private fun onSocketError(ex: Throwable) {
        Log.e("Error occurred :", "${ex.message}")
    }

    fun closeSocket(){
        repository.closeSocket()
    }

}