package br.com.movieshow.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.movieshow.AppResult
import br.com.movieshow.domain.Movie
import br.com.movieshow.dto.MovieQueryOrderBy
import br.com.movieshow.interactor.MovieInteractor
import io.reactivex.disposables.Disposable

class MainViewModel(val app: Application) : AndroidViewModel(app) {
    private val interactor = MovieInteractor(app.applicationContext)

    private var disposable: Disposable? = null

    val lastMovies = MutableLiveData<AppResult<Array<Movie>>>()

    fun getLastMovies() {

        disposable =
            interactor.lastMovies(MovieQueryOrderBy.PopularityAsc())
                .subscribe { res, error ->

                    res.forEach { movie ->
                        movie.title = "Filme: ${movie.title}"
                    }

                    if (error != null) {
                        lastMovies.value = AppResult.Error(error)
                        return@subscribe
                    }

                    lastMovies.value = AppResult.Success(res)
                }

    }

    fun getPopularMovies() {

        disposable =
            interactor.popularMovies()
                .subscribe { res, error ->

                    res.forEach { movie ->
                        movie.title = "Filme: ${movie.title}"
                    }

                    if (error != null) {
                        lastMovies.value = AppResult.Error(error)
                        return@subscribe
                    }

                    lastMovies.value = AppResult.Success(res)
                }

    }


    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}