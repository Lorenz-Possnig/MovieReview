package at.fh.swengb.possnig

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val movieAdapter = MovieAdapter() {
        val implicitIntent = Intent(this, MovieDetails::class.java)
        implicitIntent.putExtra(MOVIE_ID, it.id)
        startActivity(implicitIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MovieRepository.movieList(
            success = {movieAdapter.updateList(it)},
            error = {Toast.makeText(this, it, Toast.LENGTH_SHORT).show()}
        )
        movie_recycler_view.layoutManager = GridLayoutManager(this, 3)
        movie_recycler_view.adapter = movieAdapter
    }

    override fun onResume() {
        super.onResume()

        MovieRepository.movieList(
            success = {movieAdapter.updateList(it)},
            error = {Toast.makeText(this,it,Toast.LENGTH_SHORT).show()}
        )
    }

    companion object {
        val MOVIE_ID = "MOVIE_ID_EXTRA"
    }
}
