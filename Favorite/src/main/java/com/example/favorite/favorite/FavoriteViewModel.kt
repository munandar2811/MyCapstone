package com.example.favorite.favorite

import androidx.lifecycle.ViewModel
import com.example.core.data.domain.model.CoinModel
import com.example.core.domain.usecase.CoinUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoriteViewModel(private val useCase: CoinUseCase) : ViewModel() {

    private val _favorite = MutableStateFlow<List<CoinModel>>(ArrayList())
    val favorite: StateFlow<List<CoinModel>> = _favorite

    suspend fun onRefresh() {

        useCase
            .getAllFavorite()
            .collect {
                _favorite.value = it
            }

    }
}