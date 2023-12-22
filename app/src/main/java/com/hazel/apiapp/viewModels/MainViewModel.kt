package com.hazel.apiapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazel.apiapp.models.QuoteList
import com.hazel.apiapp.repositories.QuoteRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepositoryInterface) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    suspend fun getQuotes(page: Int):QuoteList{
        return repository.getQuotes(page)
    }
//    val quotes : LiveData<QuoteList>
//    get() = repository.quotes
}