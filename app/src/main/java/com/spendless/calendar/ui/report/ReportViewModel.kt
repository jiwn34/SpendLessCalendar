package com.spendless.calendar.ui.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.spendless.calendar.data.repository.ExpenseRepository

class ReportViewModel(
    repository: ExpenseRepository
) : ViewModel() {

    val allExpenses = repository
        .getAllExpenses()
        .asLiveData()
}
