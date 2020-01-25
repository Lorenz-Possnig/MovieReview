package at.fh.swengb.possnig

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MovieDetail(id: String,
                  title: String,
                  posterImagePath: String,
                  backdropImagePath: String,
                  release: String,
                  val plot: String,
                  val actors: List<Person>,
                  val director: Person,
                  val genres: List<String>,
                  reviews: List<Review>)
    :Movie(id, title, posterImagePath, backdropImagePath, release, reviews) {
}