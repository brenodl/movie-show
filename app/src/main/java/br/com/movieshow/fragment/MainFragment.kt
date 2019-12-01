package br.com.movieshow.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.movieshow.AppResult
import br.com.movieshow.R
import br.com.movieshow.adapter.MovieAdapter
import br.com.movieshow.databinding.MainFragmentBinding
import br.com.movieshow.domain.Movie
import br.com.movieshow.viewmodel.MainViewModel


class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this@MainFragment).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.lastMovies.observe(this) {
            updateLastMovies(it)
        }

        viewModel.getLastMovies()

        return binding.root
    }

    private fun updateLastMovies(result: AppResult<Array<Movie>>) {
        val recycleListView = binding.rvMovies
        recycleListView.layoutManager = LinearLayoutManager(context)

        when (result) {
            is AppResult.Success -> {
                // TODO: UPDATE LAST MOVIES LIST

                //result.data.forEach { movie ->
                //    Log.d("MOVIES", movie.toString())
                //}

                recycleListView.adapter = MovieAdapter(result.data, viewModel)
            }
            is AppResult.Error -> {
                if (result.error != null) {
                    Toast.makeText(
                        this.context,
                        result.error.localizedMessage,
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
}