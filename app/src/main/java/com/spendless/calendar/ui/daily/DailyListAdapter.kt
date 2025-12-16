package com.spendless.calendar.ui.daily

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spendless.calendar.R
import com.spendless.calendar.data.model.Expense

class DailyListAdapter(
    private val items: List<Expense>,
    private val onClick: (Expense) -> Unit
) : RecyclerView.Adapter<DailyListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        val tvMemo: TextView = itemView.findViewById(R.id.tvMemo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_daily_expense, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvAmount.text = "${item.amount}원"
        holder.tvMemo.text = item.memo

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
