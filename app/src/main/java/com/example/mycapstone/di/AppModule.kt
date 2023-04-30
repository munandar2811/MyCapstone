package com.example.mycapstone.di

import com.example.core.domain.usecase.CoinUseCase
import com.example.core.domain.usecase.CoinUseCaseImpl
import com.example.mycapstone.detail.DetailViewModel
import com.example.mycapstone.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<CoinUseCase> { CoinUseCaseImpl(get()) }
}

val viewModelModul = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}