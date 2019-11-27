package br.com.movieshow.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class CadastroViewModel : ViewModel() {
     val password = MutableLiveData<String>()
     val email = MutableLiveData<String>()
     val passwordConfirmar = MutableLiveData<String>()

    private lateinit var auth: FirebaseAuth
    private var createAccountSucess:Boolean = false

    fun createAccount():Boolean{
        auth = FirebaseAuth.getInstance()
        if(password.value == passwordConfirmar.value) {
            auth.createUserWithEmailAndPassword(email.value.toString(), password.value.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        this.createAccountSucess = true
                        Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    }
                }
        }
        return this.createAccountSucess
    }
}