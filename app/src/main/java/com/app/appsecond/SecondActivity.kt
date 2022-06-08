package com.app.appsecond

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {

    companion object {
        val key = "Key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val data = intent.getParcelableExtra<Data>(key)
        supportActionBar?.title = getString(R.string.app_name)
        findViewById<AppCompatTextView>(R.id.title).text = data?.way
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initList(data?.time)
    }

    // список с временем отправления-назначения и временем в пути
    private fun initList(time: List<Time>?) {
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.list_second)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        time?.let { recyclerView.adapter = SecondAdapter(it as ArrayList) { time ->
            navigateToThird(time.stations as ArrayList<Station>, time.time)
        } }
    }

    // переход на третью страницу
    private fun navigateToThird(stations: ArrayList<Station>, time: String) {
        Intent(this, ThirdActivity::class.java).also {
            it.putExtra(ThirdActivity.stationKey, stations)
            it.putExtra(ThirdActivity.timeKey, time)
            startActivity(it)
        }
    }

    // вернуться на предыдущую страницу
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}