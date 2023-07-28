package com.example.volvotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volvotest.net.bean.WeatherBean
import com.google.gson.Gson

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardInfo()
        }
    }

    @Composable
    fun CardInfo() {
        val intentData = intent.getStringExtra("detail_weather")

        val detailWeather = remember {
            Gson().fromJson(intentData, WeatherBean::class.java)
        }

        val tomorrowWeather = remember {
            detailWeather?.forecasts?.get(0)?.casts?.get(1)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                text = "${detailWeather.forecasts?.get(0)?.city}",
                style = TextStyle(fontSize = 25.sp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 4.dp,
                backgroundColor = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "明日白天风向：${tomorrowWeather?.daywind}",
                        style = TextStyle(fontSize = 14.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "明日晚上风向：${tomorrowWeather?.nightwind}",
                        style = TextStyle(fontSize = 14.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "明日白天温度：${tomorrowWeather?.daytemp} 摄氏度",
                        style = TextStyle(fontSize = 14.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "明日晚上温度：${tomorrowWeather?.nighttemp} 摄氏度",
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            }
        }
    }
}