package br.com.movieshow.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.movieshow.AppResult
import com.google.firebase.auth.FirebaseAuth

class CadastroViewModel : ViewModel() {
     val password = MutableLiveData<String>()
     val email = MutableLiveData<String>()
     val passwordConfirmar = MutableLiveData<String>()
    val result = MutableLiveData<AppResult<String>>()

    private lateinit var auth: FirebaseAuth
    fun createAccount(){
        auth = FirebaseAuth.getInstance()
        if(password.value == passwordConfirmar.value) {
            if(password.value.toString() == "" ||email.value.toString() == "" || passwordConfirmar.value.toString() == ""){
                result.value = AppResult.EmptyEmail("Senha digitada incorreta")
            }
            auth.createUserWithEmailAndPassword(email.value.toString(), password.value.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(ContentValues.TAG, "createUserWithEmail:success")
                        result.value = AppResult.Success("Conta criada com sucesso.")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                        result.value = AppResult.Error(task.exception)
                    }
                }
        }
        else {
            result.value = AppResult.IncorrectPass("Senha digitada incorreta")
        }
    }
}