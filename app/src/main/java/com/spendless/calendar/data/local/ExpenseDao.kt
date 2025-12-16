package com.spendless.calendar.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spendless.calendar.data.model.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)

    // ✅ 전체 지출 (Calendar / Report 용)
    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    // ✅ 특정 날짜 지출 (DailyList 용)
    @Query("""
        SELECT * FROM expenses
        WHERE date = :date
        ORDER BY id DESC
    """)
    fun getExpensesByDate(date: String): Flow<List<Expense>>

    @Query("DELETE FROM expenses")
    suspend fun deleteAll()
}
