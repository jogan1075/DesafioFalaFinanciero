package com.jmc.tecnicaltestfifjmc.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.jmc.tecnicaltestfifjmc.data.source.local.LocalDataSource
import com.jmc.tecnicaltestfifjmc.data.source.local.database.FifDatabase
import com.jmc.tecnicaltestfifjmc.data.source.remote.RemoteDataSource
import com.jmc.tecnicaltestfifjmc.data.source.remote.api.ApiService
import com.jmc.tecnicaltestfifjmc.domain.repository.LocalRepository
import com.jmc.tecnicaltestfifjmc.domain.repository.RemoteRepository
import com.jmc.tecnicaltestfifjmc.domain.usercase.HomeUsercase
import com.jmc.tecnicaltestfifjmc.domain.usercase.LoginUsercase
import com.jmc.tecnicaltestfifjmc.domain.usercase.RegisterUsecase
import com.jmc.tecnicaltestfifjmc.presentation.feature.home.HomeViewModel
import com.jmc.tecnicaltestfifjmc.presentation.feature.login.LoginViewModel
import com.jmc.tecnicaltestfifjmc.presentation.feature.register.RegisterViewModel
import com.jmc.tecnicaltestfifjmc.presentation.feature.splash.SplashViewModel
import com.jmc.tecnicaltestfifjmc.utils.DATABASE_NAME
import com.jmc.tecnicaltestfifjmc.utils.URL_API
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.factory
import org.koin.experimental.builder.factoryBy
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {


    single {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /* Retrofit */

    single {
        OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(URL_API)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>()
            .create(ApiService::class.java) as ApiService
    }

    /* Database */

    single {
        Room.databaseBuilder(
            androidContext(),
            FifDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    /* View models */
    viewModel<SplashViewModel>()
    viewModel<LoginViewModel>()
    viewModel<RegisterViewModel>()
    viewModel<HomeViewModel>()

    /* Factories */
    factoryBy<RemoteRepository, RemoteDataSource>()
//    factoryBy<StorageRepository, LocalCacheDataStore>()
    factoryBy<LocalRepository, LocalDataSource>()

    /* Use cases */

    factory<RegisterUsecase>()
    factory<LoginUsercase>()
    factory<HomeUsercase>()
//    factory<SearchTracksUseCase>()

}


