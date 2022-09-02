package com.example.oneword.data.api

import com.example.oneword.data.model.ResponseJson
import com.example.oneword.data.model.Translation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("queryCorpus?lang=en-ru&src=text")
    fun getMovieList(): Call<ResponseJson>

}