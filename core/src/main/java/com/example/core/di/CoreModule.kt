package com.example.core.di

import androidx.room.Room
import com.example.core.BuildConfig
import com.example.core.data.CoinRespository
import com.example.core.data.local.CoinDatabase
import com.example.core.data.remote.retrofit.ApiService
import com.example.core.domain.repository.IRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor (headerInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val roomModule = module {
    factory { get<CoinDatabase>().coinDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            CoinDatabase::class.java, "coin.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModul = module {
    single<IRepository> { CoinRespository(get(),get()) }
}

val headerInterceptor = Interceptor { chain ->
    val apiKey = BuildConfig.KEY.split(",")
    val request: Request = chain.request()
    val newRequest: Request = request.newBuilder()
        .header(apiKey[0],apiKey[1])
        .build()
    chain.proceed(newRequest)
}





