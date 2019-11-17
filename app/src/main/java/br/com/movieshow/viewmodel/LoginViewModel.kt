package br.iesb.navigation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class LoginViewModel(val app: Application) : AndroidViewModel(app) {

    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    fun login() {
        if (email.value != null && password.value != null) {
            //TODO: EFETUAR LOGIN COM FIREBASE
        }
    }

}