package  br.com.movieshow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.movieshow.R
import br.com.movieshow.databinding.ItemListMovieBinding
import br.com.movieshow.domain.Movie
import br.com.movieshow.viewmodel.MainViewModel

class MovieAdapter(
    private val domiciles: Array<Movie>,
    private val viewModel: MainViewModel
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding.movie = domiciles[position]
        binding.viewmodel = viewModel
        binding.executePendingBindings()
    }

    override fun getItemCount(): Int = domiciles.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemListMovieBinding = ItemListMovieBinding.bind(view)
    }

}