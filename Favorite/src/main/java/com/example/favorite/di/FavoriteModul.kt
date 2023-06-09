package com.example.favorite.di

import com.example.favorite.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val favoriteModul = module {
    viewModel { FavoriteViewModel(get()) }
}