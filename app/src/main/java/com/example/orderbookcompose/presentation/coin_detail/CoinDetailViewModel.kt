package com.example.orderbookcompose.presentation.coin_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderbookcompose.common.Constants
import com.example.orderbookcompose.common.Resource
import com.example.orderbookcompose.data.network.web_socket.CoinbaseWebSocketListener
import com.example.orderbookcompose.data.network.web_socket.WebSocketServiceProvider
import com.example.orderbookcompose.data.network.web_socket.dto.SocketSubscribeDto
import com.example.orderbookcompose.data.repository.WebSocketRepositoryImpl
import com.example.orderbookcompose.domain.use_case.get_coin.GetCoinUseCase
import com.example.orderbookcompose.domain.use_case.get_orderbook.GetOrderBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val repository = WebSocketRepositoryImpl(WebSocketServiceProvider(
        CoinbaseWebSocketListener(SocketSubscribeDto(
            listOf("level2"),
            listOf("BTC-USDC"),
            "subscribe"
        )))
    )

    private val getOrderBookUseCase = GetOrderBookUseCase(repository)

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    private val _newUpdate = mutableStateOf(sortedMapOf<Float, Float>())
    val newUpdate: State<SortedMap<Float, Float>> = _newUpdate

    init{
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let{ coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String){
        getCoinUseCase(coinId).onEach { result ->
            when (result){
                is Resource.Success ->{
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)

        getOrderBookUseCase().onEach { result ->

        when(result){

            is Resource.Success ->{
                //Log.e("Test", result.data.toString())
                _newUpdate.value = result.data!!
                //Log.e("Test", _newUpdate.value.toString())
            }
        }

        }.launchIn(viewModelScope)

    }

    override fun onCleared() {
        getOrderBookUseCase.closeSocket()
        super.onCleared()
    }
}