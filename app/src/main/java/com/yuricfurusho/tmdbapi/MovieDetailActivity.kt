package com.yuricfurusho.tmdbapi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.yuricfurusho.Constants.BASE_URL_IMAGES
import com.yuricfurusho.Constants.SIZE_POSTER_SIZE_W154
import com.yuricfurusho.GenreUtils
import com.yuricfurusho.model.MovieResult
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie: MovieResult =
            intent.getSerializableExtra(MovieResult::class.java.simpleName) as? MovieResult ?: MovieResult(
                title = "empty",
                releaseDate = "empty",
                overview = "empty",
                posterPath = "empty",
                genreIds = listOf(878, 28, 12)
            )

        textView_movieTitle.text = movie.title
        textView_genreList.text = movie.genreIds.map { GenreUtils.getGenreById(it) }.joinToString(", ")
        textView_releaseDate.text = movie.releaseDate
        textView_overview.text = movie.overview
        Glide.with(imageView_moviePoster)
            .load(BASE_URL_IMAGES + SIZE_POSTER_SIZE_W154 + movie.posterPath)
            .into(imageView_moviePoster)
    }
}
