package br.com.movieshow.interactor

import android.content.Context
import br.com.movieshow.domain.Movie
import br.com.movieshow.dto.MovieQueryOrderBy
import br.com.movieshow.repository.MovieRepository
import io.reactivex.Single

class MovieInteractor(context: Context) {

    private val movieRepository = MovieRepository(context)

    fun lastMovies(sortBy: MovieQueryOrderBy): Single<Array<Movie>> {
        return movieRepository.lastMovies(sortBy)
    }

}