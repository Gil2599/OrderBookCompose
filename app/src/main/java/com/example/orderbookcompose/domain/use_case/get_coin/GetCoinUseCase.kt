package com.example.orderbookcompose.domain.use_case.get_coin

import android.util.Log
import com.example.orderbookcompose.common.Resource
import com.example.orderbookcompose.data.network.dto.toCoinDetail
import com.example.orderbookcompose.data.network.dto.updateCoinPrice
import com.example.orderbookcompose.data.network.web_socket.dto.SocketResponseDto
import com.example.orderbookcompose.domain.model.CoinDetail
import com.example.orderbookcompose.domain.repository.CoinRepository
import com.example.orderbookcompose.domain.repository.WebSocketRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())

            val coin = repository.getCoinById(coinId).toCoinDetail()

            try {
                repository.getPrice(coin.symbol).updateCoinPrice(coin)

            } catch (e: HttpException) {
                emit(
                    Resource.Error<CoinDetail>(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            }

            //Log.e("Price", coin.price.toString())

            emit(Resource.Success<CoinDetail>(coin))

        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Please check internet connection"))
        }

    }

}