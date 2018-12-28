package com.yuricfurusho.tmdbapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yuricfurusho.tmdbapi.ui.movielist.MovieListFragment

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment.newInstance())
                .commitNow()
        }
    }

}
