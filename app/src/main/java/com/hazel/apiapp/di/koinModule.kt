package com.hazel.apiapp.di

import androidx.room.Room
import com.hazel.apiapp.api.QuoteService
import com.hazel.apiapp.repositories.QuoteRepository
import com.hazel.apiapp.repositories.QuoteRepositoryInterface
import com.hazel.apiapp.roomDB.QuoteDatabase
import com.hazel.apiapp.utils.QUOTES_BASE_URL
import com.hazel.apiapp.viewModels.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val koinModule = module {

    single {
        Retrofit.Builder().baseUrl(QUOTES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(QuoteService::class.java)
    }

    single<QuoteRepositoryInterface> {
        QuoteRepository(get(), get())
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            QuoteDatabase::class.java,
            "quote_database"
        ).build()
    }

    viewModel {
        MainViewModel(get())
    }
}