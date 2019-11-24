package br.com.movieshow

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    fun init(){
        Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.create())
    }
}