package com.example.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.domain.model.CoinModel
import com.example.core.data.domain.model.DetailCoinModel
import com.example.core.data.local.CoinDao
import com.example.core.data.local.entity.CoinFavoriteEntity
import com.example.core.data.remote.ApiResponse
import com.example.core.data.remote.CoinPagingSource
import com.example.core.data.remote.retrofit.ApiService
import com.example.core.domain.repository.IRepository
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class CoinRespository(private val apiService: ApiService, private val coinDao: CoinDao) :
    IRepository {

    override fun getListCoin(): Flow<PagingData<CoinModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 0
            ),
            pagingSourceFactory = {
                CoinPagingSource(apiService)
            }
        ).flow

    }

    override suspend fun getDetailCoin(id: Int): Flow<ApiResponse<DetailCoinModel>> {

        return flow {
            try {
                val response = apiService.getDetailCoin(id)


                if (response.data != null) {

                    val detailCoin = DataMapper.detailResponToModel(id.toString(), response)
                    emit(ApiResponse.Success(detailCoin))
                } else {
                    emit(ApiResponse.Error("API request failed: ${response.status?.errorMessage}"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error("API request failed: ${e.message}"))
            }
        }


    }

    override suspend fun getAllFavorite(): Flow<List<CoinModel>> {
        return flow {
            val coinModel = coinDao.getAllFavorite()
            emit(DataMapper.coinEntityToModel(coinModel))
        }
    }

    override fun isFavorite(id: Int): Flow<Boolean> {
        return flow {
            val isFavorite = coinDao.isFavorite(id)

            emit(isFavorite)
        }
    }

    override suspend fun deleteFavorite(id: Int) {
        coinDao.deleteFavorite(id)
    }

    override suspend fun updateFavorite(coinEntity: CoinFavoriteEntity) {
        coinDao.updateFavorite(coinEntity)
    }

    override suspend fun insertFavorite(coinEntity: CoinFavoriteEntity) {
        coinDao.insertFavorite(coinEntity)
    }

}