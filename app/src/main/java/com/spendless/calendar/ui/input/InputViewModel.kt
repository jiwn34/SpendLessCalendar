package com.spendless.calendar.ui.input

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.spendless.calendar.data.DatabaseModule
import com.spendless.calendar.data.model.Expense
import kotlinx.coroutines.launch

class InputViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        DatabaseModule.provideRepository(application)

    fun insertExpense(expense: Expense) {
        viewModelScope.launch {
            repository.insertExpense(expense)
        }
    }
}
