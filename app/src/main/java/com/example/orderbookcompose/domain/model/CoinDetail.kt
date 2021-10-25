package com.example.orderbookcompose.domain.model

import com.example.orderbookcompose.data.network.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>?,
    var price: Float? = null
)

