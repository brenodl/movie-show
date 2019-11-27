package br.com.movieshow.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {

    private val BASE_URL:String = "http://api.themoviedb.org/3/"

    fun init(){
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
}