package br.com.movieshow.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Auth {
    private lateinit var auth: FirebaseAuth

    init {
        auth = FirebaseAuth.getInstance()
    }

    fun loginUser(email:String, password:String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    //Registration OK

                } else {
                    //Registration error
                }
            }
    }


}