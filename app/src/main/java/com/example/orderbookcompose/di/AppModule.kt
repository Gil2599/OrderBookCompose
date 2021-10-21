package com.example.orderbookcompose.di

import com.example.orderbookcompose.common.Constants
import com.example.orderbookcompose.common.Constants.SOCKET_BASE_URL
import com.example.orderbookcompose.data.network.CoinPaprikaApi
import com.example.orderbookcompose.data.network.CoinbaseApi
import com.example.orderbookcompose.data.repository.CoinRepositoryImpl
import com.example.orderbookcompose.domain.repository.CoinRepository
import com.example.orderbookcompose.domain.repository.SocketService
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.retry.ExponentialWithJitterBackoffStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi{
        return Retrofit.Builder()
            .baseUrl(Constants.PAPRIKA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinbaseApi(): CoinbaseApi{
        return Retrofit.Builder()
            .baseUrl(Constants.COINBASE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinbaseApi::class.java)
    }

    /*@Provides
    @Singleton
    fun provideWebSocketService(): SocketService{
        return Scarlet.Builder()
            .webSocketFactory(okHttpClient.newWebSocketFactory(SOCKET_BASE_URL))
            .addMessageAdapterFactory(MoshiMessageAdapter.Factory())
            .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
            .backoffStrategy(ExponentialWithJitterBackoffStrategy(5000, 5000))
            .lifecycle(lifecycle)
            .build()
            .create()
    }*/


    @Provides
    @Singleton
    fun provideCoinRepository(paprikaApi: CoinPaprikaApi, coinbaseApi: CoinbaseApi): CoinRepository{
        return CoinRepositoryImpl(paprikaApi, coinbaseApi)
    }
}