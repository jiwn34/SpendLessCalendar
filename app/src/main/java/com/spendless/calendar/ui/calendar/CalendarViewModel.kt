package com.spendless.calendar.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.spendless.calendar.data.repository.ExpenseRepository

class CalendarViewModel(
    repository: ExpenseRepository
) : ViewModel() {

    // 🔥 CalendarFragment에서 사용하는 이름과 반드시 일치해야 함
    val allExpenses = repository
        .getAllExpenses()
        .asLiveData()
}
