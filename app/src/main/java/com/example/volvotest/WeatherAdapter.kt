package com.example.volvotest

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.volvotest.WeatherAdapter.WeatherViewHolder
import com.example.volvotest.net.bean.WeatherBean
import com.google.gson.Gson

class WeatherAdapter(private val dataList: List<WeatherBean>) :
    RecyclerView.Adapter<WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val tomorrowWeather = dataList[position].forecasts?.get(0)?.casts?.get(1)
        holder.cityNameTv.text = dataList[position].forecasts?.get(0)?.city ?: "数据出错"
        holder.dateTv.text = tomorrowWeather?.date ?: "数据出错"
        holder.dayTempTv.text = "白天气温：${tomorrowWeather?.daytemp}"
        holder.dayWeatherTv.text = "白天气象：" + tomorrowWeather?.dayweather
        holder.nightTempTv.text = "夜晚温度：" + tomorrowWeather?.nighttemp
        holder.nightWeatherTv.text = "夜晚气象：" + tomorrowWeather?.nightweather
        holder.itemView.setOnClickListener {
            val gson = Gson()
            val toJson = gson.toJson(dataList[position])
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra("detail_weather", toJson)
            val activityOptions =
                ActivityOptions.makeSceneTransitionAnimation(
                    it.context as Activity,
                    holder.cityNameTv,
                    "content_share"
                )
            it.context.startActivity(
                intent,
                activityOptions.toBundle()
            )
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityNameTv: TextView = itemView.findViewById(R.id.id_item_cityName_tv)
        val dateTv: TextView = itemView.findViewById(R.id.id_item_date_tv)
        val dayWeatherTv: TextView = itemView.findViewById(R.id.id_item_dayWeather_tv)
        val nightWeatherTv: TextView = itemView.findViewById(R.id.id_item_nightWeather_tv)
        val dayTempTv: TextView = itemView.findViewById(R.id.id_item_dayTemp_tv)
        val nightTempTv: TextView = itemView.findViewById(R.id.id_item_nightTemp_tv)

    }
}