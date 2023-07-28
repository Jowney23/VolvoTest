package com.example.volvotest

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.volvotest.net.ServerOperator
import com.example.volvotest.net.bean.WeatherBean
import io.reactivex.disposables.Disposable

class MainViewModel : ViewModel(), DefaultLifecycleObserver {
    private lateinit var mDisposable: Disposable
    private val mWeatherLiveData = MutableLiveData<List<WeatherBean>>()


    //北京 110000
    //上海 310000
    //广州 440100
    //深圳 440300
    //苏州 320500
    //沈阳 210100
    private val mCityCodeList =
        arrayListOf<String>("110000", "310000", "440100", "440300", "320500", "210100")

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        mDisposable = ServerOperator.operatorWeatherData(mCityCodeList).subscribe({
            mWeatherLiveData.postValue(it)
        }, {
        })
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        if (!mDisposable.isDisposed) mDisposable.dispose()
    }

    fun getWeatherLiveData(): MutableLiveData<List<WeatherBean>> {
        return mWeatherLiveData
    }
}