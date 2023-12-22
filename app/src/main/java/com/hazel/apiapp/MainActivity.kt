package com.hazel.apiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hazel.apiapp.adapters.QuoteAdapter
import com.hazel.apiapp.databinding.ActivityMainBinding
import com.hazel.apiapp.viewModels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
//    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var quoteAdapter: QuoteAdapter
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(binding.root)

        quoteAdapter = QuoteAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = quoteAdapter

        CoroutineScope(Dispatchers.IO).launch {
            val quoteList = viewModel.getQuotes(1)
            Log.d("QuotelistShow", "$quoteList")
            withContext(Dispatchers.Main) {
                quoteAdapter.quotes = quoteList.results
            }
        }
        /*val repository = (application as MyApp).quoteRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)*/
//
//        viewModel.quotes.observe(this, Observer {quoteList ->
//            quoteList?.let {
//                quoteAdapter.quotes = it.results
//            }
////            Log.d("CheckingPurpose", it.results.toString())
////            Toast.makeText(this, it.results.size.toString(), Toast.LENGTH_SHORT).show()
//        })
    }
}