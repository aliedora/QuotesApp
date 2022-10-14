package com.e.quotesapp.common

import RetrofitServices
import com.e.quotesapp.retrofit.RetrofitClient


object Common {
    private const val BASE_URL = "https://animechan.vercel.app/api/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}