package br.com.movieshow.viewmodel

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.movieshow.AppResult

import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordViewModel : ViewModel() {

    val email = MutableLiveData<String>()
    val result = MutableLiveData<AppResult<String>>()

    private lateinit var auth: FirebaseAuth

    fun sendPasswordResetEmail() {
        auth = FirebaseAuth.getInstance()
        if (!TextUtils.isEmpty(email.value)) {
            auth.sendPasswordResetEmail(email.value.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val message = "E-mail enviado com sucesso."
                        result.value = AppResult.Success(message)
                        Log.d(TAG, message)
                    } else {
                        Log.w(TAG, task.exception!!.message)
                        result.value = AppResult.Error(task.exception)
                    }
                }
        } else {
            result.value = AppResult.EmptyEmail("Preencha o campo de e-mail")
        }
    }
}
