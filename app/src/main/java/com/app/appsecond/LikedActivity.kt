package com.app.appsecond

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LikedActivity : AppCompatActivity() {

    companion object {
        val key = "likeKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibrannie)
        supportActionBar?.title = getString(R.string.app_name)
        intent.getParcelableArrayListExtra<Data>(key)?.let { initList(it) }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    // список важных направлений с условием пустого списка
    private fun initList(list: ArrayList<Data>) {
        val favoriteList = list.filter { item -> item.liked }
        if (favoriteList.isEmpty()) {
            findViewById<AppCompatTextView>(R.id.list_is_empty).visibility = View.VISIBLE
        } else {
            val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.list_like)
            recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = MainAdapter(favoriteList as ArrayList<Data>, {
                navigateToSecond(it)
            }, {}, false)
        }
    }

    // переход на вторую страницу
    private fun navigateToSecond(item: Data) {
        Intent(this, SecondActivity::class.java).also {
            it.putExtra(SecondActivity.key, item)
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