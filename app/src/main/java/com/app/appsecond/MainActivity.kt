package com.app.appsecond

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var data = Data.initData()

    private val adapter by lazy {
        MainAdapter(data, { item ->
            navigateToSecond(item)
        }, { index ->
            swapData(index)
        }, true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.app_name)
        initList()

        // поиск
        findViewById<SearchView>(R.id.poisk).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(it: String?): Boolean {
                val query = it.toString()
                adapter.filter.filter(query)
                return false
            }
        })
    }

    // список направлений
    private fun initList() {
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.list_main)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }


    // переход на вторую страницу
    private fun navigateToSecond(item: Data) {
        Intent(this, SecondActivity::class.java).also {
            it.putExtra(SecondActivity.key, item)
            startActivity(it)
        }
    }

    //  изменение значения liked при клике на иконку напротив соответствующего направления
    private fun swapData(index: Int) {
        val itemClickedId = data[index].id
        val itemInList = data.first { data -> data.id == itemClickedId }
        itemInList.liked = !itemInList.liked
        adapter.notifyItemChanged(index)
    }

    // создания меню, там где иконка избранное
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // прослушивание кликов в меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_favorite -> {
                navigateToLiked()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // переход на страницу с важными направлениями
    private fun navigateToLiked() {
        Intent(this, LikedActivity::class.java).also {
            it.putExtra(LikedActivity.key, data)
            startActivity(it)
        }
    }

}