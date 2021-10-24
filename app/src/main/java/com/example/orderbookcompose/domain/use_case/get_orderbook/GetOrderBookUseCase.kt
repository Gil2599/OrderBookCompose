package com.example.orderbookcompose.domain.use_case.get_orderbook

import android.util.Log
import com.example.orderbookcompose.common.Resource
import com.example.orderbookcompose.data.network.web_socket.CoinbaseWebSocketListener
import com.example.orderbookcompose.data.network.web_socket.dto.SocketResponseDto
import com.example.orderbookcompose.domain.model.CoinDetail
import com.example.orderbookcompose.domain.repository.WebSocketRepository
import com.google.gson.Gson
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import javax.inject.Inject

class GetOrderBookUseCase (
    webSocketRepository: WebSocketRepository
) {
    val repository = webSocketRepository

    operator fun invoke() : Flow<Resource<CoinbaseWebSocketListener.SocketUpdate>> = flow {

        try {

            repository.startSocket().consumeEach {

                if (it.exception == null) {

                    Log.e( "TEST In UseCase", Gson().fromJson(it.text, SocketResponseDto::class.java).toString() )
                    emit(Resource.Success<CoinbaseWebSocketListener.SocketUpdate>(it))

                } else {
                    Log.e("ERROR", "Calling closeSocket...")
                    repository.closeSocket()
                    onSocketError(it.exception)

                }
            }


        } catch (e: HttpException){
            emit(
                Resource.Error<CoinbaseWebSocketListener.SocketUpdate>(
                    e.localizedMessage ?: "An unexpected error occurred")
            )
        }
    }

    private fun onSocketError(ex: Throwable) {
        Log.e("Error occurred :", "${ex.message}")
    }

    fun closeSocket(){
        repository.closeSocket()
    }

}