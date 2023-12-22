package com.hazel.apiapp.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hazel.apiapp.models.Result

@Dao
interface QuoteDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    /*suspend*/ fun addQuote(quotes: List<Result>)

    @Query("SELECT * FROM quoteTable")
    /*suspend*/ fun getQuotes() : List<Result>
}