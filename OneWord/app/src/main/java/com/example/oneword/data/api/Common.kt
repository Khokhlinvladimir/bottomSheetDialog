package com.example.oneword.data.api

object Common {

    private const val BASE_URL = "https://dictionary.yandex.net/dicservice.json/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)

}