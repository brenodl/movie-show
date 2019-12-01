package br.com.movieshow.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.movieshow.AppResult
import br.com.movieshow.activity.LoginActivity
import br.com.movieshow.fragment.LoginFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(val app: Application) : AndroidViewModel(app) {

    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val result = MutableLiveData<AppResult<String>>()

    private lateinit var auth: FirebaseAuth

     fun login() {
        if(email.value != null && password.value != null){
            loginUser(email.value.toString(), password.value.toString())
        }
    }

    fun loginUser(email:String, password:String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCustomToken:success")
                    result.value = AppResult.Success("Login Realizado com sucesso")
                } else {
                    Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                    result.value = AppResult.Error(task.exception)
                }
            }
    }
}