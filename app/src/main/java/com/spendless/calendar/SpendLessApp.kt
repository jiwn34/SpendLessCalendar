package com.spendless.calendar

import android.app.Application
import com.spendless.calendar.data.local.AppDatabase
import com.spendless.calendar.data.repository.ExpenseRepository

class SpendLessApp : Application() {

    val database: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }

    val repository: ExpenseRepository by lazy {
        ExpenseRepository(database.expenseDao())
    }
}
