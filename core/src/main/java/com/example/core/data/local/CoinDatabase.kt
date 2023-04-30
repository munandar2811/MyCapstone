package com.example.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.local.entity.CoinFavoriteEntity

@Database(entities = [CoinFavoriteEntity::class], version = 1)
abstract class CoinDatabase: RoomDatabase() {
    abstract fun coinDao () : CoinDao
}