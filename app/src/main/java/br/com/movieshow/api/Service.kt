package br.com.movieshow.api

import br.com.movieshow.dto.MovieResultDTO
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("discover/movie")
    fun lastMovies(): Call<List<MovieResultDTO>>
}
