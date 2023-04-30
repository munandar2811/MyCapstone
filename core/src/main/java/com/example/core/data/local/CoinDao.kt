package com.example.core.data.local

import androidx.room.*
import com.example.core.data.local.entity.CoinFavoriteEntity

@Dao
interface CoinDao {

    @Query("SELECT * From coin_favorite")
    suspend fun getAllFavorite():List<CoinFavoriteEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM coin_favorite WHERE id = :id)")
    suspend fun isFavorite(id:Int):Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(entity: CoinFavoriteEntity)

    @Query("DELETE FROM coin_favorite WHERE id = :id")
    suspend fun deleteFavorite(id:Int)

    @Update
    suspend fun updateFavorite(entity: CoinFavoriteEntity)

}