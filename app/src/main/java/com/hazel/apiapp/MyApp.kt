package com.hazel.apiapp

import android.app.Application
import com.hazel.apiapp.api.QuoteService
import com.hazel.apiapp.api.RetrofitHelper
import com.hazel.apiapp.di.koinModule
import com.hazel.apiapp.repositories.QuoteRepository
import com.hazel.apiapp.roomDB.QuoteDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
//        initialize()
        startKoin {
            androidContext(this@MyApp)
            modules(koinModule)
        }
    }

    /*private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database)
    }*/
}