package at.fh.swengb.possnig

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class Movie(val id: String,
                 val title: String,
                 val posterImagePath: String,
                 val backdropImagePath: String,
                 val release: String,
                 val reviews: List<Review>) {



    fun ratingAverage(): Double = reviews.map{x -> x.reviewValue}.average()
}