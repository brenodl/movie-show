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

    fun createAccount(){
        auth = FirebaseAuth.getInstance()
        if(password.value == passwordConfirmar.value) {
            auth.createUserWithEmailAndPassword(email.value.toString(), password.value.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(ContentValues.TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    }
                }
        }
    }
}
