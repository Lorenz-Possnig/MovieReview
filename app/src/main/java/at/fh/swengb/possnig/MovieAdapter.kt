package at.fh.swengb.possnig

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val clickListener: (movie: Movie) -> Unit): RecyclerView.Adapter<MovieViewHolder>() {

    private var movieList = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from((parent.context))
        val movieItemView = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(movieItemView, clickListener)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(movieList[position])
    }

    fun updateList(newlist: List<Movie>) {
        movieList = newlist
        notifyDataSetChanged()
    }
}

class MovieViewHolder(itemView: View, val clickListener: (movie: Movie) -> Unit): RecyclerView.ViewHolder(itemView) {

    fun bindItem(movie: Movie) {
        var average = 0.0

        if (movie.reviews.isNotEmpty()) {
            average = movie.ratingAverage()
        }

        Glide.with(itemView)
            .load(movie.posterImagePath)
            .apply(RequestOptions().centerCrop())
            .transform(RoundedCorners(20))
            .placeholder(R.drawable.ic_image_black_24dp)
            .into(itemView.moviePoster)
        itemView.movieTitle.text = movie.title
        itemView.setOnClickListener() {
            clickListener(movie)
        }
    }
}