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

import br.com.movieshow.databinding.CadastroFragmentBinding
import br.com.movieshow.viewmodel.CadastroViewModel

class CadastroFragment : Fragment() {

    private lateinit var binding: CadastroFragmentBinding

    private val viewModel: CadastroViewModel by lazy {
        ViewModelProviders.of(this@CadastroFragment).get(CadastroViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CadastroFragmentBinding.inflate(inflater, container, false)

        binding.viewmodel = viewModel
        binding.fragment = this@CadastroFragment
        binding.lifecycleOwner = this

        viewModel.result.observe(this@CadastroFragment) {
            when (it) {
                is AppResult.Success -> {
                    Toast.makeText(
                        this.context,
                        it.data,
                        Toast.LENGTH_LONG
                    ).show()
                    findNavController().navigate(R.id.action_forgotPasswordFragment_to_LoginFragment)
                }
                is AppResult.EmptyEmail -> {
                    Toast.makeText(
                        this.context,
                        it.email,
                        Toast.LENGTH_LONG
                    ).show()
                }
                is AppResult.IncorrectPass -> {
                    Toast.makeText(
                        this.context,
                        it.passwordVerify,
                        Toast.LENGTH_LONG
                    ).show()
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
}
