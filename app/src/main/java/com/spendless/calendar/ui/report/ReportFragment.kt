package com.spendless.calendar.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.spendless.calendar.databinding.FragmentReportBinding
import com.spendless.calendar.data.model.Expense

class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReportViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allExpenses.observe(viewLifecycleOwner) { expenses: List<Expense> ->

            if (expenses.isEmpty()) {
                binding.tvSummary.text = "데이터만 없음"
                return@observe
            }

            val total = expenses.sumOf { expense ->
                expense.amount
            }

            binding.tvSummary.text = "총 지출: ${total}원"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
