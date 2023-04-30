package com.example.core.domain.usecase

import androidx.paging.PagingData
import com.example.core.data.domain.model.CoinModel
import com.example.core.data.domain.model.DetailCoinModel
import com.example.core.data.local.entity.CoinFavoriteEntity
import com.example.core.data.remote.ApiResponse
import com.example.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class CoinUseCaseImpl(private val repository: IRepository) : CoinUseCase {


    override fun getListCoin(): Flow<PagingData<CoinModel>> {
        return repository.getListCoin()
    }

    override suspend fun getDetailCoin(id: Int): Flow<ApiResponse<DetailCoinModel>> {
        return repository.getDetailCoin(id = id)
    }

    override suspend fun getAllFavorite(): Flow<List<CoinModel>> {
        return repository.getAllFavorite()
    }

    override fun isFavorite(id: Int): Flow<Boolean> {
        return repository.isFavorite(id)
    }

    override suspend fun deleteFavorite(id: Int) {
        repository.deleteFavorite(id)
    }

    override suspend fun updateFavorite(coinEntity: CoinFavoriteEntity) {
        repository.updateFavorite(coinEntity)
    }

    override suspend fun insertFavorite(coinEntity: CoinFavoriteEntity) {
        repository.insertFavorite(coinEntity)
    }


}