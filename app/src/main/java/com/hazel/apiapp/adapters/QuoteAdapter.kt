package com.hazel.apiapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.hazel.apiapp.R
import com.hazel.apiapp.databinding.ItemQuoteBinding
import com.hazel.apiapp.models.Result
import com.hazel.apiapp.viewModels.MainViewModel

class QuoteAdapter : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    var quotes: List<Result> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemQuoteBinding = DataBindingUtil.inflate(inflater, R.layout.item_quote, parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val currentQuote = quotes[position]
        holder.bind(currentQuote)
    }

    override fun getItemCount(): Int = quotes.size

    inner class QuoteViewHolder(private val binding: ItemQuoteBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
            binding.quote = result
//            binding.executePendingBindings()

            // Set a click listener for the share button
            binding.ibShare.setOnClickListener {
                // Handle share click if needed
                val context = binding.root.context
                val content = result.content
                shareContent(context, content)
            }
        }

        private fun shareContent(context: Context, content: String) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, content)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }
    }
}