package com.example.core.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.domain.model.CoinModel
import com.example.core.data.remote.retrofit.ApiService
import com.example.core.utils.DataMapper

class CoinPagingSource (private val apiService: ApiService): PagingSource<Int, CoinModel> () {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
        const val LIMIT = 50
    }

    override fun getRefreshKey(state: PagingState<Int, CoinModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinModel> {

        val position = params.key ?: INITIAL_PAGE_INDEX
        val start = (position-1)*50+1
        val responseData = apiService.getListCoin(start = start, limit = LIMIT).data

        return try {
            LoadResult.Page(
                data = DataMapper.coinResponToModel(responseData),
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.isNullOrEmpty()) null else position + 1
            )
        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }


}