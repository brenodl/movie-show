package br.com.movieshow.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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

        return binding.root
    }

    fun cadastro(view: View) {
        viewModel.createAccount()
        findNavController().navigate(R.id.action_loginFragment_to_loginFragment)
    }
}
