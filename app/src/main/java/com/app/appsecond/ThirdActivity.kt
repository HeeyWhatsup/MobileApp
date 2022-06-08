package com.app.appsecond

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ThirdActivity : AppCompatActivity() {

    companion object {
        val stationKey = "stationKey"
        val timeKey = "timeKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val data = intent.getParcelableArrayListExtra<Station>(stationKey)
        supportActionBar?.title = getString(R.string.app_name)
        findViewById<AppCompatTextView>(R.id.title).text = "Остановочный пункт"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initList(data)
    }

    // список с остановками и временем прибытия на них
    private fun initList(time: List<Station>?) {
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.list_third
        )
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        time?.let { recyclerView.adapter = ThirdAdapter(it as ArrayList) }
    }

    // вернуться на предыдущую страницу
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}