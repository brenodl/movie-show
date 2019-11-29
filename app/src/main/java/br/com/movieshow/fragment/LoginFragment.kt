package br.com.movieshow.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.com.movieshow.AppResult

import br.com.movieshow.R
import br.com.movieshow.databinding.LoginFragmentBinding
import br.com.movieshow.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this@LoginFragment).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)

        binding.viewmodel = viewModel
        binding.fragment = this@LoginFragment
        binding.lifecycleOwner = this

        viewModel.result.observe(this@LoginFragment) {
            when (it) {
                is AppResult.Success -> {
                    Toast.makeText(
                        this.context,
                        it.data,
                        Toast.LENGTH_LONG
                    ).show()
                    findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                    activity?.finish()
                }
                is AppResult.Error -> {
                    if (it.error != null) {
                        Toast.makeText(
                            this.context,
                            it.error.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val genericError = this.context?.getString(R.string.generic_error)
                        Toast.makeText(
                            this.context,
                            genericError,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
            return binding.root
        }
        fun forgotPassword(view: View) {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        fun cadastro(view: View) {
            findNavController().navigate(R.id.action_loginFragment_to_cadastroFragment)
        }

}
