package at.fh.swengb.possnig

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_movie_rating.*

class MovieRatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_rating)

        val movieID = intent.getStringExtra(MovieDetails.MOVIE_ID)

        if (movieID != null) {
            MovieRepository.movieById(movieID,
                success = {
                    rating_title.text = it.title
                    rate_button.setOnClickListener(){
                        MovieRepository.rateMovie(movieID,
                            Review(
                                rating_rating_bar.rating.toDouble(),
                                feedback.text.toString()
                            ),
                            success = {
                                Toast.makeText(this, "Thank you for your review", Toast.LENGTH_SHORT)
                                setResult(Activity.RESULT_OK)
                                finish()
                            },
                            error = {
                                Toast.makeText(this, "Something went wrong\nPlease try again later", Toast.LENGTH_SHORT)
                                setResult(Activity.RESULT_CANCELED)
                                finish()
                            }
                        )
                    }
                },
                error = {
                    Toast.makeText(this, "Movie not found\nPlease try again later", Toast.LENGTH_SHORT)
                    setResult(Activity.RESULT_CANCELED)
                    finish()
                }
            )
        }
        else {
            setResult((Activity.RESULT_CANCELED))
            finish()
        }
    }
}
