package com.example.mycapstone.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.data.domain.model.CoinModel
import com.example.core.domain.usecase.CoinUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(useCase: CoinUseCase) : ViewModel() {
    companion object {
        const val DEFAULT_TIMEOUT = 5000L
    }

    val listCoinModel: StateFlow<PagingData<CoinModel>> = useCase.getListCoin()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(DEFAULT_TIMEOUT),
            initialValue = PagingData.empty()
        )
}