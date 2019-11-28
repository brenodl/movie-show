package br.com.movieshow.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.movieshow.activity.LoginActivity
import br.com.movieshow.fragment.LoginFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(val app: Application) : AndroidViewModel(app) {

    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    private lateinit var auth: FirebaseAuth
    private var logado:Boolean = false

    fun login() : Boolean{

        auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            logado = true
        }else if (email.value != null && password.value != null) {
            loginUser(email.value.toString(),password.value.toString())
        }
        return logado
    }

    fun isLogado(): Boolean{
        auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            return true
        }
        return false
    }

    fun logoff(){
        FirebaseAuth.getInstance().signOut()
    }

    fun loginUser(email:String, password:String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCustomToken:success")
                    this.logado = true
                } else {
                    Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                }
            }
    }
}