package at.fh.swengb.possnig

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_movie_details.*
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)


        val movieID = intent.getStringExtra(MainActivity.MOVIE_ID)

        if (movieID != null) {
            MovieRepository.movieById(movieID,
                success =
                {
                    var average = 0f
                    if (it.reviews.isNotEmpty()) {average = it.ratingAverage().toFloat()}
                    movieRelease.text = it.release
                    movieRatingBar.rating = average
                    movieRatingAvg.text = java.lang.String.format("%.2f", it.ratingAverage())
                    movieRatingNum.text = it.reviews.size.toString()
                    movieTitle.text = it.title
                    movieActors.text = it.actors.map { x -> x.name }.joinToString()
                    movieGenres.text = it.genres.joinToString()
                    movieDirector.text = it.director.name
                    moviePlot.text = it.plot
                    Glide.with(this)
                        .load(it.posterImagePath)
                        .transform(RoundedCorners(20))
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .into(this.moviePoster)
                    Glide.with(this)
                         .load(it.backdropImagePath)
                         .placeholder(R.drawable.ic_image_black_24dp)
                         .into(this.movieBackdrop)
                },
                error = {
                    Toast.makeText(this, "Movie not found", Toast.LENGTH_SHORT).show()
                    finish()
                })

            movieRateButton.setOnClickListener {
                val intent = Intent(this, MovieRatingActivity::class.java)
                intent.putExtra(MOVIE_ID,movieID)
                startActivityForResult(intent, MOVIE_RATED)
            }
        }
        else {
            Log.v("finished","Movie couldn't be found by ID")
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MOVIE_RATED && resultCode == Activity.RESULT_OK) {
            Log.v("Rating","Rating was added")
            Toast.makeText(this, "Rating was added", Toast.LENGTH_LONG).show()
            finish()
        }
        else {
            Log.v("Rating","Rating activity closed by user")
            Toast.makeText(this, "No new rating added", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    companion object {
        val MOVIE_ID = "MOVIE_ID"
        val MOVIE_RATED = 1
    }
}
