package com.spendless.calendar.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.spendless.calendar.R
import com.spendless.calendar.SpendLessApp
import com.spendless.calendar.data.model.Expense
import com.spendless.calendar.databinding.FragmentCalendarBinding
import com.spendless.calendar.ui.ExpenseViewModelFactory
import java.util.Calendar

class CalendarFragment : Fragment() {

    private val viewModel: CalendarViewModel by viewModels {
        ExpenseViewModelFactory(
            (requireActivity().application as SpendLessApp).repository
        )
    }

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private val calendar: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMonthNavigation()
        observeExpenses()
    }

    // ◀ ▶ 월 이동
    private fun setupMonthNavigation() {
        binding.btnPrevMonth.setOnClickListener {
            calendar.add(Calendar.MONTH, -1)
            observeExpenses()
        }

        binding.btnNextMonth.setOnClickListener {
            calendar.add(Calendar.MONTH, 1)
            observeExpenses()
        }
    }

    /**
     * DB에서 전체 Expense를 받아
     * 날짜별 합계를 계산해서 캘린더에 표시
     */
    private fun observeExpenses() {

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) // 0-based

        binding.tvMonth.text = "${year}년 ${month + 1}월"

        val dayList = generateDayList(year, month)
        val monthStr = String.format("%02d", month + 1)

        viewModel.allExpenses.observe(viewLifecycleOwner) { expenses: List<Expense> ->

            val dailySumMap = mutableMapOf<Int, Int>()

            expenses.forEach { expense ->
                // yyyy-MM-dd
                if (expense.date.startsWith("$year-$monthStr")) {
                    val day = expense.date.substring(8, 10).toInt()
                    dailySumMap[day] =
                        dailySumMap.getOrDefault(day, 0) + expense.amount
                }
            }

            val adapter = CalendarAdapter(
                days = dayList,
                dailySumMap = dailySumMap
            ) { selectedDay ->

                if (selectedDay == 0) return@CalendarAdapter

                val dayStr = String.format("%02d", selectedDay)
                val dateString = "$year-$monthStr-$dayStr"

                val bundle = Bundle().apply {
                    putString("selectedDate", dateString)
                }

                findNavController().navigate(
                    R.id.dailyListFragment,
                    bundle
                )
            }

            binding.recyclerDays.layoutManager =
                GridLayoutManager(requireContext(), 7)
            binding.recyclerDays.adapter = adapter
        }
    }

    // 날짜 리스트 생성
    private fun generateDayList(year: Int, month: Int): List<Int> {
        val list = mutableListOf<Int>()

        val temp = Calendar.getInstance()
        temp.set(year, month, 1)

        val startDay = temp.get(Calendar.DAY_OF_WEEK) - 1
        val totalDays = temp.getActualMaximum(Calendar.DAY_OF_MONTH)

        repeat(startDay) {
            list.add(0)
        }

        for (day in 1..totalDays) {
            list.add(day)
        }

        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
