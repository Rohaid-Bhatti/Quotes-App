package com.hazel.apiapp.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hazel.apiapp.models.Result

@Database(entities = [Result::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao() : QuoteDAO

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        QuoteDatabase::class.java,
                        "quote_database"
                    )
                        /*.createFromAsset("quotes.db")*/
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}