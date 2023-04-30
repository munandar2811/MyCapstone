package com.example.core.data.domain.model

data class CoinModel(
    val id:Int?,
    val rank: Int?,
    val name: String?,
    val symbol: String?,
    val logoUrl: String?,
    val price: Double,
    val percentChange24h: Double,
    val isFavorite:Boolean? = false
)

