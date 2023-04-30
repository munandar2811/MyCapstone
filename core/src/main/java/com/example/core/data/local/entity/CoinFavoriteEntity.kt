package com.example.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "coin_favorite")
data class CoinFavoriteEntity(
    @PrimaryKey (autoGenerate = false)
    val id:Int?,
    val rank: Int?,
    val name: String?,
    val symbol: String?,
    val logoUrl: String?,
    val price: Double,
    val percentChange24h: Double
)
