package com.app.appsecond

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class ThirdAdapter(private val data: ArrayList<Station>) : RecyclerView.Adapter<ThirdAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val tvStation = view.findViewById<AppCompatTextView>(R.id.title_station)
        private val tvTime = view.findViewById<AppCompatTextView>(R.id.title_time)

        fun bind(stationDetail: Station) {
            tvTime.text = stationDetail.time
            tvStation.text = stationDetail.station
        }
    }

    // возвращает объект ViewHolder, который будет хранить данные по одному объекту ThirdActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_second, parent, false)
        return ViewHolder(itemView)
    }

    // выполняет привязку объекта ViewHolder к объекту ThirdActivity по определенной позиции
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size
}