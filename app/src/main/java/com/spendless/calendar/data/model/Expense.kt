package com.spendless.calendar.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val date: String,      // yyyy-MM-dd
    val amount: Int,
    val memo: String,
    val category: String  // 🔥 AI 분류 결과
)
