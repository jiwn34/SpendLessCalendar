package com.spendless.calendar.data

import android.content.Context
import androidx.room.Room
import com.spendless.calendar.data.local.AppDatabase
import com.spendless.calendar.data.repository.ExpenseRepository

object DatabaseModule {

    private var database: AppDatabase? = null

    private fun getDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "spendless_db"
            ).build()
            database = instance
            instance
        }
    }

    fun provideRepository(context: Context): ExpenseRepository {
        val db = getDatabase(context)
        return ExpenseRepository(db.expenseDao())
    }
}
