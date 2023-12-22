package com.hazel.apiapp.repositories

import com.hazel.apiapp.api.QuoteService
import com.hazel.apiapp.models.QuoteList
import com.hazel.apiapp.roomDB.QuoteDatabase
import com.hazel.apiapp.models.Result

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase
) : QuoteRepositoryInterface {

//    private val quoteLiveData = MutableLiveData<QuoteList>()

//    val quotes : LiveData<QuoteList>
//    get() = quoteLiveData

    /*suspend fun getQuotes(page: Int) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = quoteService.getQuotes(page)
            if (result?.body() != null) {
                quoteDatabase.quoteDao().addQuote(result.body()!!.results)
                quoteLiveData.postValue(result.body())
            }
        } else {
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1,1, 1, quotes, 1, 1)
            quoteLiveData.postValue(quoteList)
        }
    }*/
    override suspend fun getQuotes(page: Int):QuoteList {
        try {
            val result = quoteService.getQuotes(page)

            return if (result.body() != null) {
                quoteDatabase.quoteDao().addQuote(result.body()!!.results)
                val quotesFromDatabase: List<Result> = quoteDatabase.quoteDao().getQuotes()
                val quoteList = QuoteList(1, 1, 1, quotesFromDatabase, 1, 1)
                quoteList
            } else {
                throw Exception("Result body is null")
            }
        } catch (e: Exception) {
            // Handle network errors or other exceptions
            e.printStackTrace()

            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1, 1, 1, quotes, 1, 1)
            return quoteList
        }
    }
}