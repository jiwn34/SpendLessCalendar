package com.spendless.calendar.ui.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.spendless.calendar.SpendLessApp
import com.spendless.calendar.databinding.FragmentDailyListBinding
import com.spendless.calendar.ui.ExpenseViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DailyListFragment : Fragment() {

    private var _binding: FragmentDailyListBinding? = null
    private val binding get() = _binding!!

    // 🔥🔥🔥 핵심 수정 포인트 (Factory 사용)
    private val viewModel: DailyListViewModel by viewModels {
        ExpenseViewModelFactory(
            (requireActivity().application as SpendLessApp).repository
        )
    }

    private lateinit var dailyAdapter: DailyExpenseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 세팅
        dailyAdapter = DailyExpenseAdapter()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = dailyAdapter

        // 📌 CalendarFragment에서 전달한 날짜 받기
        val selectedDate = arguments?.getString("selectedDate")
        if (selectedDate != null) {
            viewModel.loadExpensesByDate(selectedDate)
        }

        // 데이터 수집
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.expenseList.collectLatest { list ->
                dailyAdapter.submitList(list)
                binding.emptyView.visibility =
                    if (list.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
