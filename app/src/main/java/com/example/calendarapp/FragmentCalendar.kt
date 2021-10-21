package com.example.calendarapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.adapter.CalendarAdapter
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class FragmentCalendar(var selectedDate: LocalDate) : Fragment() {

    lateinit var monthYearText: TextView
    lateinit var calendarRecyclerView: RecyclerView
    lateinit var spSelectDay: Spinner
    var monthArr = mutableListOf<Int>()
    var weekdays = mutableListOf("SUN", "MON", "TUE", "WED", "THUR", "FRI", "SAT")
    var startDay = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_calendar, container, false)
        spSelectDay = view.findViewById(R.id.spSelectDay)
        var tvDay1 : TextView = view.findViewById(R.id.tv_day1)
        var tvDay2 : TextView = view.findViewById(R.id.tv_day2)
        var tvDay3 : TextView = view.findViewById(R.id.tv_day3)
        var tvDay4 : TextView = view.findViewById(R.id.tv_day4)
        var tvDay5 : TextView = view.findViewById(R.id.tv_day5)
        var tvDay6 : TextView = view.findViewById(R.id.tv_day6)
        var tvDay7 : TextView = view.findViewById(R.id.tv_day7)
        spSelectDay.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                startDay = p2
                var listRotate =weekdays.rotate(-p2)
                tvDay1.text = listRotate[0]
                tvDay2.text = listRotate[1]
                tvDay3.text = listRotate[2]
                tvDay4.text = listRotate[3]
                tvDay5.text = listRotate[4]
                tvDay6.text = listRotate[5]
                tvDay7.text = listRotate[6]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        calendarRecyclerView = view.findViewById(R.id.recyclerview_day_of_month)
        monthYearText = view.findViewById(R.id.month_year_text)
//        selectedDate = LocalDate.now()

        monthYearText.setText(monthYearFromDate(selectedDate))



        var dayInMonth: ArrayList<String> = daysInMonthArray(selectedDate)
        var calendarAdapter = CalendarAdapter(dayInMonth)
        var layoutManager = GridLayoutManager(context, 7)
        calendarRecyclerView.layoutManager = layoutManager
        calendarRecyclerView.setHasFixedSize(true)

        var itemDecorationVertical: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        var itemDecorationHorizontal: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        calendarRecyclerView.addItemDecoration(itemDecorationVertical)
        calendarRecyclerView.addItemDecoration(itemDecorationHorizontal)

        calendarRecyclerView.adapter = calendarAdapter

        return view
    }
    fun <T> MutableList<T>.rotate(distance: Int) =
        toList().also {
            Collections.rotate(it, distance)
        }


    private fun daysInMonthArray(date: LocalDate?): ArrayList<String> {
        var daysInMonthArray: ArrayList<String> = ArrayList()
        var yearMonth: YearMonth = YearMonth.from(date)
        var daysInMonth = yearMonth.lengthOfMonth()
        var firstOfMonth: LocalDate = selectedDate.withDayOfMonth(1)
        var dayOfWeek: Int = firstOfMonth.dayOfWeek.value

        var dateOfMontPre = YearMonth.from(date?.minusMonths(1)).lengthOfMonth()


        for(i in 1..42){
            if(i<=dayOfWeek){
                daysInMonthArray.add((dateOfMontPre-dayOfWeek+i).toString()+"*")
            }else{
                if(i>daysInMonth + dayOfWeek){
                    daysInMonthArray.add((i-daysInMonth-dayOfWeek).toString()+"*")
                }
                else{
                    daysInMonthArray.add((i - dayOfWeek).toString())
                }
            }
        }
        return daysInMonthArray
    }

    fun monthYearFromDate(date: LocalDate): String{
        var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }
}