package com.spendless.calendar.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spendless.calendar.data.repository.ExpenseRepository
import com.spendless.calendar.ui.calendar.CalendarViewModel
import com.spendless.calendar.ui.daily.DailyListViewModel
import com.spendless.calendar.ui.report.ReportViewModel

class ExpenseViewModelFactory(
    private val repository: ExpenseRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CalendarViewModel::class.java) ->
                CalendarViewModel(repository) as T

            modelClass.isAssignableFrom(DailyListViewModel::class.java) ->
                DailyListViewModel(repository) as T

            modelClass.isAssignableFrom(ReportViewModel::class.java) ->
                ReportViewModel(repository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
