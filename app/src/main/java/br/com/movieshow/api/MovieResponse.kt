package br.com.movieshow.api

import br.com.movieshow.models.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("results")
    var movies: List<Movie>
)