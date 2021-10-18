package com.example.orderbookcompose.domain.use_case.get_coin

import com.example.orderbookcompose.common.Resource
import com.example.orderbookcompose.data.network.dto.toCoin
import com.example.orderbookcompose.data.network.dto.toCoinDetail
import com.example.orderbookcompose.domain.model.Coin
import com.example.orderbookcompose.domain.model.CoinDetail
import com.example.orderbookcompose.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow{
        try{
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage?: "An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Please check internet connection"))
        }
    }
}