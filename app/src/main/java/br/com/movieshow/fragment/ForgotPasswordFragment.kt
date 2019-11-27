package br.com.movieshow.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import br.com.movieshow.R
import br.com.movieshow.viewmodel.ForgotPasswordViewModel
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = ForgotPasswordFragment()
    }
    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forgot_password_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun recuperarSenha(view: View){
        viewModel.recuperarSenha()
        Toast.makeText(this.context, "Authentication failed.", Toast.LENGTH_SHORT).show()
    }
}
