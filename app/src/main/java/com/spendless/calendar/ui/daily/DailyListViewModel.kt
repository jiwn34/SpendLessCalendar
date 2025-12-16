package com.spendless.calendar.ui.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spendless.calendar.data.repository.ExpenseRepository
import com.spendless.calendar.data.model.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DailyListViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {

    private val _expenseList = MutableStateFlow<List<Expense>>(emptyList())
    val expenseList: StateFlow<List<Expense>> = _expenseList

    fun loadExpensesByDate(date: String) {
        viewModelScope.launch {
            repository.getExpensesByDate(date)
                .collectLatest {
                    _expenseList.value = it
                }
        }
    }
}
