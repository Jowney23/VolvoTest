package com.example.volvotest

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.id_ma_rv


class MainActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycle.addObserver(mainViewModel)
        val recyclerView = id_ma_rv
        recyclerView.layoutManager = LinearLayoutManager(this)
        mainViewModel.getWeatherLiveData().observe(this) { weatherList ->
            recyclerView.adapter = WeatherAdapter(weatherList)
        }
    }
}