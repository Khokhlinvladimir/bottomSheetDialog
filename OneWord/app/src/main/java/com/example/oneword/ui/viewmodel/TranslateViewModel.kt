package com.example.oneword.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.oneword.data.api.*
import com.example.oneword.data.model.ResponseJson
import com.example.oneword.data.model.Translation
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class TranslateViewModel(application: Application, text: String) : ViewModel() {

    var mService: RetrofitServices = Common.retrofitService

    init {
        getAllMovieList()
    }

    val liveData = MutableLiveData<List<String>>()
    val liveData2 = MutableLiveData<List<ResponseJson>>()

    fun setData (newText: String) {
        val sdf = SimpleDateFormat("dd.MM.yy", Locale.ENGLISH)
        val timestamp = Timestamp(System.currentTimeMillis())
        liveData.value = listOf(newText, " - Привет", sdf.format(timestamp), "US")
    }

    private fun getAllMovieList() {

        mService.getMovieList().enqueue(object : Callback<ResponseJson> {
            override fun onFailure(call: Call<ResponseJson>, t: Throwable) {
            }

            override fun onResponse(call: Call<ResponseJson>, response: Response<ResponseJson>) {
                liveData2.value = listOf(response.body() as ResponseJson)

            }
        })
    }




    }





