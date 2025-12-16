package com.spendless.calendar.ui.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spendless.calendar.R

class CalendarAdapter(
    private val days: List<Int>,                 // 날짜 리스트
    private val dailySumMap: Map<Int, Int>,       // 날짜별 지출 합계
    private val onDayClick: (Int) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.DayViewHolder>() {

    inner class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDay: TextView = itemView.findViewById(R.id.tvDay)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar_day, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = days[position]

        if (day == 0) {
            // 빈 칸
            holder.tvDay.text = ""
            holder.tvAmount.text = ""
            holder.itemView.setOnClickListener(null)
        } else {
            holder.tvDay.text = day.toString()

            val sum = dailySumMap[day] ?: 0
            holder.tvAmount.text = if (sum > 0) "${sum}원" else ""

            holder.itemView.setOnClickListener {
                onDayClick(day)
            }
        }
    }

    override fun getItemCount(): Int = days.size
}
