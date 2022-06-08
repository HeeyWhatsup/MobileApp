package com.app.appsecond

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter(private val data: ArrayList<Data>,
                  val dataClick:(item: Data) -> Unit,
                  val toLikeClick:(index: Int) -> Unit, val isMainPage: Boolean)

    : RecyclerView.Adapter<MainAdapter.ViewHolder>(), Filterable {

    private var dataFiltered: ArrayList<Data> = ArrayList()

    init {
        dataFiltered = data
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val titleWay = view.findViewById<AppCompatTextView>(R.id.title_way)
        val toLike = view.findViewById<AppCompatImageButton>(R.id.to_like)
        fun bind(data: Data) {
            titleWay.text = data.way
            if (isMainPage) {
                if (data.liked) {
                    toLike.setImageDrawable(
                        AppCompatResources.getDrawable(
                            view.context,
                            R.drawable.ic_favorex
                        )
                    )
                } else {
                    toLike.setImageDrawable(
                        AppCompatResources.getDrawable(
                            view.context,
                            R.drawable.ic_favor
                        )
                    )
                }
            } else {
                toLike.visibility = View.GONE
            }
        }
    }

    // возвращает объект ViewHolder, который будет хранить данные по одному объекту MainActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(itemView)
    }

    // выполняет привязку объекта ViewHolder к объекту MainActivity по определенной позиции
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataFiltered[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            dataClick.invoke(item)
        }
        holder.toLike.setOnClickListener {
            toLikeClick.invoke(position)
        }
    }

    override fun getItemCount() = dataFiltered.size

    // поиск направоений
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charSearch = charSequence.toString()
                if (charSearch.isEmpty()) {
                    dataFiltered = data
                } else {
                    val resultList = ArrayList<Data>()
                    for (row in data) {
                        if (row.way.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    dataFiltered = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFiltered
                return filterResults
            }
            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                dataFiltered = results?.values as ArrayList<Data>
                notifyDataSetChanged()
            }
        }
    }
}