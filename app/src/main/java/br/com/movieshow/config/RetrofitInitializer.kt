package br.com.movieshow.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    fun init(){
        Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.create()).build()
    }
}