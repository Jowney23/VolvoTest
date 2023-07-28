package com.example.volvotest.net

import com.example.volvotest.net.bean.WeatherBean
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

object ServerOperator {
    private fun operate(): ServerApi {
        return RetrofitMaster.getInstance().serverApi as ServerApi
    }

    fun operatorWeatherData(cityCodeList: List<String>): Observable<List<WeatherBean>> {
        val requestList = mutableListOf<Observable<WeatherBean>>()
        cityCodeList.forEach { cityCode ->
            requestList.add(operate().apiDataWeather(city = cityCode))
        }

        return Observable.zip(requestList) { zipResult ->
            zipResult.toList() as List<WeatherBean>
        }.subscribeOn(Schedulers.io())
    }


}