package com.example.calendarapp.adapter

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.calendarapp.FragmentCalendar
import java.time.LocalDate

class ViewPagerAdapter(var fragmentList: MutableList<FragmentCalendar>, fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCalendar(selectedDate: LocalDate){
        Log.d("TAG", "selectedDate nhan duoc: ${selectedDate.month}")
        fragmentList[0] = FragmentCalendar.newInstance(selectedDate.minusMonths(1))
        fragmentList[1] = FragmentCalendar.newInstance(selectedDate)
        fragmentList[2] = FragmentCalendar.newInstance(selectedDate.plusMonths(1))
        notifyDataSetChanged()
    }
    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}