package com.example.orderbookcompose.domain.use_case.get_coin

import android.util.Log
import com.example.orderbookcompose.common.Resource
import com.example.orderbookcompose.data.network.dto.toCoinDetail
import com.example.orderbookcompose.data.network.dto.updateCoinPrice
import com.example.orderbookcompose.domain.model.CoinDetail
import com.example.orderbookcompose.domain.repository.CoinRepository
import io.reactivex.Observable
import io.reactivex.Observable.interval
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import java.util.concurrent.Flow.Subscription
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow{
        try{
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()

            /*try{
                val subscription: Subscription = Observable.interval(
                    1000, 5000,
                    TimeUnit.MILLISECONDS
                )
                    .subscribe(object : Action1<Long?>() {
                        fun call(aLong: Long?) {
                            // here is the task that should repeat
                        }
                    })
                repository.getPrice(coin.symbol).updateCoinPrice(coin)
            } catch (e: HttpException){
                emit(Resource.Error<CoinDetail>(e.localizedMessage?: "An unexpected error occurred"))
                }*/

            Log.e("Test", coin.price.toString())

            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage?: "An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Please check internet connection"))
        }
    }
}