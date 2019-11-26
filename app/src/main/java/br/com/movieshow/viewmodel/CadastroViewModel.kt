package br.com.movieshow.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CadastroViewModel : ViewModel() {
    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()
}
