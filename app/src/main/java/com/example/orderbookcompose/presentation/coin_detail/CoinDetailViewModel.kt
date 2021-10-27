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
import kotlinx.coroutines.flow.*
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

    private val _stateCoin = mutableStateOf(CoinDetailState())
    val stateCoin: State<CoinDetailState> = _stateCoin

    private val _stateBook = MutableStateFlow(CoinOderBookState())
    val stateBook: StateFlow<CoinOderBookState> = _stateBook

    private val _newUpdate = MutableStateFlow<List<Pair<Float, Float>>>(emptyList())

    //val newUpdate: StateFlow<List<Pair<Float, Float>>> = _newUpdate.asStateFlow()

    init{
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let{ coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String){
        getCoinUseCase(coinId).onEach { result ->
            when (result){
                is Resource.Success ->{
                    _stateCoin.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _stateCoin.value = CoinDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _stateCoin.value = CoinDetailState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)

        getOrderBookUseCase().onEach { result ->

        when(result){

            is Resource.Success ->{
                //Log.e("Test", result.data.toString())
                _stateBook.value = CoinOderBookState(orderBook = result.data!!.toList().map { Pair(it.first, it.second)})
                //_newUpdate.value = result.data.toList().map { Pair(it.first, it.second) }

                //Log.e("Test", newUpdate.value.toString())

            }

            is Resource.Loading -> {
                _stateBook.value = CoinOderBookState(isLoading = true)
            }
        }

        }.launchIn(viewModelScope)

    }

    override fun onCleared() {
        getOrderBookUseCase.closeSocket()
        super.onCleared()
    }
}