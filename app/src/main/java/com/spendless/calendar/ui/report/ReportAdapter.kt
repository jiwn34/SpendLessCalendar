package com.spendless.calendar.ui.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spendless.calendar.R
import com.spendless.calendar.data.model.Expense

class ReportAdapter(
    private val items: List<Expense>
) : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    inner class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        val tvMemo: TextView = itemView.findViewById(R.id.tvMemo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_report, parent, false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val item = items[position]
        holder.tvDate.text = item.date
        holder.tvAmount.text = "${item.amount}원"
        holder.tvMemo.text = item.memo
    }

    override fun getItemCount(): Int = items.size
}
