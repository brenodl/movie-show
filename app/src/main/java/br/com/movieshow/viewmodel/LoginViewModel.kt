package br.com.movieshow.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(val app: Application) : AndroidViewModel(app) {

    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    private lateinit var auth: FirebaseAuth

    fun login() {
        auth = FirebaseAuth.getInstance()
        Log.d(TAG, "entrei aqui")
        if (email.value != null && password.value != null) {
            loginUser(email.toString(),password.toString())
        }
    }

    fun loginUser(email:String, password:String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCustomToken:success")
                    val user = auth.currentUser
                } else {
                    Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                    Toast.makeText(this.getApplication(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}