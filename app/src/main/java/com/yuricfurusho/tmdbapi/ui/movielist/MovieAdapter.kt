package com.yuricfurusho.tmdbapi.ui.movielist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.yuricfurusho.Constants.BASE_URL_IMAGES
import com.yuricfurusho.Constants.SIZE_POSTER_SIZE_W154
import com.yuricfurusho.GenreUtils
import com.yuricfurusho.model.MovieResult
import com.yuricfurusho.tmdbapi.R
import kotlinx.android.synthetic.main.adapter_movie_item.view.*

class MovieAdapter(
    private val mListener: MovieListAdapterListener
) : RecyclerView.Adapter<MovieAdapter.MovieListViewHolder>() {

    private var mListMovie = mutableListOf<MovieResult>()

    override fun getItemCount(): Int = mListMovie.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movieResult = mListMovie[position]
        holder.mMovieTitle.text = movieResult.title
        holder.mGenreList.text = movieResult.genreIds.map { GenreUtils.getGenreById(it) }.joinToString(", ")
        holder.mReleaseDate.text = movieResult.releaseDate
        Glide.with(holder.mImgMoviePoster).load(BASE_URL_IMAGES + SIZE_POSTER_SIZE_W154 + movieResult.posterPath)
            .into(holder.mImgMoviePoster)

        holder.mView.setOnClickListener { v -> mListener.onItemClick(v, position) }
    }

    fun setList(movieList: List<MovieResult>) {
        mListMovie.clear()
        mListMovie.addAll(movieList)
        notifyDataSetChanged()
    }


    inner class MovieListViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mMovieTitle: TextView = mView.textView_movieTitle
        val mImgMoviePoster: ImageView = mView.imageView_moviePoster
        val mGenreList: TextView = mView.textView_genreList
        val mReleaseDate: TextView = mView.textView_releaseDate
    }

    interface MovieListAdapterListener {
        fun onItemClick(view: View, position: Int)
    }

}
