package com.example.oneword.data.model

import com.example.oneword.data.api.RetrofitServices

class RetrofitRepository(private val api: RetrofitServices) {


     fun addRequest() {
         api.getMovieList()
    }




}