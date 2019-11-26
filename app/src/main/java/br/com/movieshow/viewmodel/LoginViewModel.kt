package br.com.movieshow.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
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
        if (email.value != null && password.value != null) {
            loginUser(email.value.toString(),password.value.toString())
            return logado
        }
        return false
    }

    fun loginUser(email:String, password:String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCustomToken:success")
                    this.logado = true
                    val user = auth.currentUser
                } else {
                    Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                }
            }
    }

    fun createAccount(){
        auth.createUserWithEmailAndPassword(email.value.toString(), password.value.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)

                }
            }
    }
}