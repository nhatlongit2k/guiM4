package com.example.calendarapp.adapter

import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.R


class CalendarAdapter(var dayOfMonth: ArrayList<String>) : RecyclerView.Adapter<CalendarAdapter.MyViewHolder>() {

    var row_index = -1
    var index_double_click = -1
    var click_time =0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        var inflate = LayoutInflater.from(parent.context)
//        var view = inflate.inflate(R.layout.calendar_day, parent, false)
//        var layoutParams: ViewGroup.LayoutParams = view.layoutParams
//        layoutParams.height = ((parent.height * 0.166666666).toInt())
//        return MyViewHolder(view)

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (dayOfMonth[position].contains("*")){
            holder.dayOfMonth.isEnabled = false
            var textDay = dayOfMonth[position].substring(0, dayOfMonth[position].length-1)
            holder.dayOfMonth.setText(textDay)
        }else{
            holder.dayOfMonth.isEnabled = true
            holder.dayOfMonth.setText(dayOfMonth[position])
        }
        if (row_index==position) {
            holder.dayOfMonth.setBackgroundColor(Color.RED);
        } else if(index_double_click == position){
            holder.dayOfMonth.setBackgroundColor(Color.BLUE)
        }else{
            holder.dayOfMonth.setBackgroundColor(Color.WHITE);
        }

        holder.dayOfMonth.setOnClickListener {
//            row_index = position
//            notifyDataSetChanged()
            click_time++
            val handler = Handler()
            handler.postDelayed({
                if(click_time == 1){
                    index_double_click = -1
                    row_index = position
                    notifyDataSetChanged()
                }else if(click_time==2){
                    index_double_click = position
                    row_index = -1
                    notifyDataSetChanged()
                }
                click_time = 0
            }, 200)
        }



    }

    override fun getItemCount(): Int {
        return dayOfMonth.size
    }
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dayOfMonth: TextView = itemView.findViewById(R.id.tv_date)
    }
}