package com.example.mycapstone.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.local.entity.CoinFavoriteEntity
import com.example.core.data.remote.ApiResponse
import com.example.core.domain.usecase.CoinUseCase
import com.example.mycapstone.main.MainViewModel.Companion.DEFAULT_TIMEOUT
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: CoinUseCase) : ViewModel() {

    private val _isCoinFavorite = MutableStateFlow(false)
    val isCoinFavorite: StateFlow<Boolean> = _isCoinFavorite

    suspend fun detail(id: Int) = useCase.getDetailCoin(id)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(DEFAULT_TIMEOUT),
            initialValue = ApiResponse.Loading
        )

    fun toggleFavorite(id: Int,coinFavoriteEntity: CoinFavoriteEntity) {
        val isFavorite = _isCoinFavorite.value
        val newIsFavorite = !isFavorite
        _isCoinFavorite.value = newIsFavorite

        viewModelScope.launch {
            if (newIsFavorite) {
                useCase.insertFavorite(coinEntity = coinFavoriteEntity)
            } else {
                useCase.deleteFavorite(id)
            }
        }
    }

    fun observeFavoriteStatus(id: Int) {
        viewModelScope.launch {
            useCase.isFavorite(id).collect { favoriteCoin ->
                _isCoinFavorite.value = favoriteCoin
            }
        }
    }


}