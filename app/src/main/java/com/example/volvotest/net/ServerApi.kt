package com.example.volvotest.net


import com.example.volvotest.net.bean.WeatherBean
import io.reactivex.Observable
import retrofit2.http.*

interface ServerApi {
    @GET("v3/weather/weatherInfo")
    fun apiDataWeather(
        @Query("key") key: String = "399006931f095ec9efceeca9e38102da",
        @Query("extensions") extensions: String = "all",
        @Query("city") city: String = "",
    ): Observable<WeatherBean>

}
