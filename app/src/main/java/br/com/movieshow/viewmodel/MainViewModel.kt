package br.com.movieshow.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.movieshow.AppResult
import br.com.movieshow.api.Client
import br.com.movieshow.domain.Movie

class MainViewModel(val app: Application) : AndroidViewModel(app) {

    val lastMovies = MutableLiveData<AppResult<Array<Movie>>>()
    val api = Client().init()
    fun getLastMovies() {
        lastMovies.value

    }

}