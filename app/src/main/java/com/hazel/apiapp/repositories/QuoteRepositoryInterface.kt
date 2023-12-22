package com.hazel.apiapp.repositories

import com.hazel.apiapp.models.QuoteList

interface QuoteRepositoryInterface {

    suspend fun getQuotes(page: Int):QuoteList
}