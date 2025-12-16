package com.spendless.calendar.ui.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.spendless.calendar.data.model.Expense
import com.spendless.calendar.databinding.ItemDailyExpenseBinding

class DailyExpenseAdapter :
    ListAdapter<Expense, DailyExpenseAdapter.ExpenseViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseViewHolder {
        val binding = ItemDailyExpenseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ExpenseViewHolder(
        private val binding: ItemDailyExpenseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(expense: Expense) {
            binding.tvMemo.text = expense.memo
            binding.tvCategory.text = expense.category
            binding.tvAmount.text = "${expense.amount}원"
        }
    }

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<Expense>() {
                override fun areItemsTheSame(
                    oldItem: Expense,
                    newItem: Expense
                ): Boolean = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: Expense,
                    newItem: Expense
                ): Boolean = oldItem == newItem
            }
    }
}
