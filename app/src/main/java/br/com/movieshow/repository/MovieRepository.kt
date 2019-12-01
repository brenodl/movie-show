package br.com.movieshow.repository

import android.content.Context
import br.com.movieshow.api.Service
import br.com.movieshow.domain.Movie
import br.com.movieshow.dto.MovieQueryOrderBy
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MovieRepository(val context: Context) :
    RetrofitBase(context, "https://api.themoviedb.org/3/")
{
    private val movieService = retrofit.create(Service::class.java)

    fun lastMovies(sortBy: MovieQueryOrderBy): Single<Array<Movie>> {

        return movieService.lastMovies(sortBy.value)
            .map { result ->

                val list = mutableListOf<Movie>()
                result.movies.forEach {
                    list.add(it)
                }
                list.toTypedArray()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }
    fun PopularMovies(): Single<Array<Movie>> {

        return movieService.PopularMovies()
            .map { result ->

                val list = mutableListOf<Movie>()
                result.movies.forEach {
                    list.add(it)
                }
                list.toTypedArray()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

}