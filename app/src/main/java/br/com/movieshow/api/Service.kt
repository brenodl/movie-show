package br.com.movieshow.api

import br.com.movieshow.dto.MovieResultDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("discover/movie")
    fun lastMovies(@Query("sort_by") sortBy: String): Single<MovieResultDTO>
}
