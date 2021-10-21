package com.example.calendarapp.adapter

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.calendarapp.FragmentCalendar
import java.time.LocalDate

class ViewPager2Adapter(var fragmentList: ArrayList<FragmentCalendar>, activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun setCalendar(selectedDate: LocalDate){
//        Log.d("TAG", "selectedDate nhan duoc: ${selectedDate.month}")
        fragmentList[0] = FragmentCalendar(selectedDate.minusMonths(1))
        fragmentList[1] = FragmentCalendar(selectedDate)
        Log.d("TAG", "Frag: ${fragmentList.get(1).selectedDate}")
        fragmentList[2] = FragmentCalendar(selectedDate.plusMonths(1))
        //notifyDataSetChanged()

        //Log.d("TAG", "selectedDate2: ${selectedDate.month}")
    }

}