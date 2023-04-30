package com.example.mycapstone

import android.app.Application
import com.example.core.di.*
import com.example.mycapstone.di.useCaseModule
import com.example.mycapstone.di.viewModelModul
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    roomModule,
                    networkModule,
                    repositoryModul,
                    useCaseModule,
                    viewModelModul
                )
            )
        }
    }

}