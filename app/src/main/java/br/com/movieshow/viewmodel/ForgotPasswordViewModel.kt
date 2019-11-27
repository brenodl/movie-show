package br.com.movieshow.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordViewModel : ViewModel() {
    val email = MutableLiveData<String>()

    private lateinit var auth: FirebaseAuth

    fun recuperarSenha(){
        auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email.value.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "recuperaSenha:success")
                } else {
                    Log.w(ContentValues.TAG, "recuperaSenha:failure", task.exception)
                }
            }
    }


}
