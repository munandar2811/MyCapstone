package com.example.core.domain.repository

import androidx.paging.PagingData
import com.example.core.data.domain.model.CoinModel
import com.example.core.data.domain.model.DetailCoinModel
import com.example.core.data.local.entity.CoinFavoriteEntity
import com.example.core.data.remote.ApiResponse
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun getListCoin(): Flow<PagingData<CoinModel>>

   suspend fun getDetailCoin(id:Int):Flow<ApiResponse<DetailCoinModel>>

   suspend fun getAllFavorite():Flow<List<CoinModel>>

   fun isFavorite(id:Int):Flow<Boolean>

   suspend fun deleteFavorite(id: Int)

   suspend fun updateFavorite(coinEntity: CoinFavoriteEntity)

   suspend fun insertFavorite(coinEntity: CoinFavoriteEntity)

}