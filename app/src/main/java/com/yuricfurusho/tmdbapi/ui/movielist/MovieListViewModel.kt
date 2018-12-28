package com.yuricfurusho.tmdbapi.ui.movielist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.yuricfurusho.model.MovieResult
import com.yuricfurusho.tmdbapi.ui.movielist.MovieListViewModel.ENVIRONMENT.REMOTE

class MovieListViewModel : ViewModel() {
    enum class ENVIRONMENT { DUMMY, REMOTE }

    private lateinit var environment: MutableLiveData<ENVIRONMENT>
    private lateinit var loading: MutableLiveData<Boolean>
    private lateinit var mMovieList: MutableLiveData<List<MovieResult>>

    fun getLoading(): LiveData<Boolean> {
        if (!::loading.isInitialized) {
            loading = MutableLiveData()
            loading.value = false
        }
        return loading
    }

    fun getEnvironment(): LiveData<ENVIRONMENT> {
        if (!::environment.isInitialized) {
            environment = MutableLiveData()
            environment.value = REMOTE
        }
        return environment
    }

    fun setEnvironment(value: ENVIRONMENT) {
        if (environment.value != value) {
            environment.value = value
            loadMovieList()
        }
    }

    fun getMovieList(): LiveData<List<MovieResult>> {
        if (!::mMovieList.isInitialized) {
            mMovieList = MutableLiveData()
            loadMovieList()
        }
        return mMovieList
    }

    fun loadMovieList() {
        loading.value = true
        when (getEnvironment().value) {
            ENVIRONMENT.DUMMY -> loadDummyMovieList()
            REMOTE -> loadMovieListFromRemote()
        }
    }

    private fun loadDummyMovieList() {
        val mMovieList = mutableListOf<MovieResult>()

        for (i in 1..100) {
            mMovieList.add(
                MovieResult(
                    title = "title-$i",
                    releaseDate = "2019-01-${i % 28 +1}",
                    overview = "movie overview",
                    posterPath = "/xPihqTMhCh6b8DHYzE61jrIiNMS.jpg",
                    genreIds = listOf(878, 28, 12)
                )
            )
        }

        this.mMovieList.value = mMovieList
        loading.value = false
    }

    private fun loadMovieListFromRemote() {
        //TODO
        mMovieList.value = mutableListOf<MovieResult>()
        loading.value = false
    }

    fun loadNextPage() {
        loading.value = true
        when (getEnvironment().value) {
            ENVIRONMENT.DUMMY -> loadNextPageDummyMovieList()
            REMOTE -> loadNextPageMovieListFromRemote()
        }
    }

    private fun loadNextPageDummyMovieList() {
        val mMovieList = mutableListOf<MovieResult>()

        val list: List<MovieResult>? = this.mMovieList.value
        list?.let { mMovieList.addAll(list) }

        for (i in mMovieList.size..mMovieList.size + 100) {
            mMovieList.add(
                MovieResult(
                    title = "title-$i",
                    releaseDate = "2019-01-${i % 28 +1}",
                    overview = "movie overview",
                    posterPath = "/xPihqTMhCh6b8DHYzE61jrIiNMS.jpg",
                    genreIds = listOf(878, 28, 12)
                )
            )
        }

        this.mMovieList.value = mMovieList
        loading.value = false
    }

    private fun loadNextPageMovieListFromRemote() {
        // todo
        mMovieList.value = mutableListOf<MovieResult>()
        loading.value = false
    }
}
