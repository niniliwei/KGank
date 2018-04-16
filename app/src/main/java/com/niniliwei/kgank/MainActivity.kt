package com.niniliwei.kgank

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.niniliwei.kgank.api.ApiService
import com.niniliwei.kgank.api.RetrofitManager
import com.niniliwei.kgank.api.ioThread
import com.niniliwei.kgank.api.mainThread
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val apiService = RetrofitManager.createService(ApiService::class.java)
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(this)
        recycler_view.adapter = adapter

        refresh_layout.setOnRefreshListener { refresh() }

        refresh()
    }

    private fun refresh() {
        if (!refresh_layout.isRefreshing) refresh_layout.isRefreshing = true

        apiService.getWelfareList(20, 1)
                .subscribeOn(ioThread)
                .observeOn(mainThread)
                .doAfterTerminate {
                    if (refresh_layout.isRefreshing) refresh_layout.isRefreshing = false
                }
                .map { it.results }
                .subscribe { response, throwable ->
                    adapter.setNewData(response)
                }
    }
}
