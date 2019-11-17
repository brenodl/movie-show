package br.iesb.navigation.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import br.iesb.navigation.R
import br.iesb.navigation.databinding.LoginFragmentBinding
import br.iesb.navigation.viewmodel.LoginViewModel

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

        return binding.root
    }

    fun login(view: View) {

    }

    fun forgotPassword(view: View) {
        findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
    }

}
