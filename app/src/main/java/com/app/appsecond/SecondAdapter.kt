package com.app.appsecond

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class SecondAdapter(private val data: ArrayList<Time>,
                    val onClick:(item: Time) -> Unit,) : RecyclerView.Adapter<SecondAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val tvStation = view.findViewById<AppCompatTextView>(R.id.title_station)
        private val tvTime = view.findViewById<AppCompatTextView>(R.id.title_time)
        private val tvTime_way = view.findViewById<AppCompatTextView>(R.id.title_way)

        fun bind(time: Time) {
            tvTime.text = time.time
            tvTime_way.text = time.time_way
            tvStation.visibility = View.GONE
        }
    }

    // возвращает объект ViewHolder, который будет хранить данные по одному объекту SecondActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_second, parent, false)
        return ViewHolder(itemView)
    }

    // выполняет привязку объекта ViewHolder к объекту SecondActivity по определенной позиции
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClick.invoke(item)
        }
    }

    override fun getItemCount() = data.size
}