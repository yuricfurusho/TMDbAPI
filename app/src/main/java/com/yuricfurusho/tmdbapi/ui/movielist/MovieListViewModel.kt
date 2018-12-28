package com.yuricfurusho.tmdbapi.ui.movielist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.yuricfurusho.model.MovieResult
import com.yuricfurusho.model.UpcomingResult
import com.yuricfurusho.repository.MovieListRepository
import com.yuricfurusho.tmdbapi.ui.movielist.MovieListViewModel.ENVIRONMENT.REMOTE

class MovieListViewModel : ViewModel() {
    enum class ENVIRONMENT { DUMMY, REMOTE }

    private lateinit var environment: MutableLiveData<ENVIRONMENT>
    private lateinit var loading: MutableLiveData<Boolean>
    private lateinit var mMovieList: MutableLiveData<List<MovieResult>>
    var mOriginalMovieList: MutableList<MovieResult> = mutableListOf()
    private val repository = MovieListRepository(this)


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
        val movieList = mutableListOf<MovieResult>()

        for (i in 1..100) {
            movieList.add(
                MovieResult(
                    title = "title-$i",
                    releaseDate = "2019-01-${i % 28 +1}",
                    overview = "movie overview",
                    posterPath = "/xPihqTMhCh6b8DHYzE61jrIiNMS.jpg",
                    genreIds = listOf(878, 28, 12)
                )
            )
        }

        mMovieList.value = movieList
        loading.value = false
        mOriginalMovieList.clear()
        mOriginalMovieList.addAll(mMovieList.value ?: mutableListOf<MovieResult>())
    }

    private fun loadMovieListFromRemote() {
        repository.requestStories()
    }

    fun setResponse(upcomingResult: UpcomingResult?) {
        mMovieList.value = upcomingResult?.results?.sortedByDescending { it.releaseDate } ?: mutableListOf<MovieResult>()
        loading.value = false
        mOriginalMovieList.clear()
        mOriginalMovieList.addAll(mMovieList.value ?: mutableListOf<MovieResult>())
    }

    fun loadNextPage() {
        loading.value = true
        when (getEnvironment().value) {
            ENVIRONMENT.DUMMY -> loadNextPageDummyMovieList()
            REMOTE -> loadNextPageMovieListFromRemote()
        }
    }

    private fun loadNextPageDummyMovieList() {
        val movieList = mutableListOf<MovieResult>()

        val list: List<MovieResult>? = mMovieList.value
        list?.let { movieList.addAll(list) }

        for (i in movieList.size..movieList.size + 100) {
            movieList.add(
                MovieResult(
                    title = "title-$i",
                    releaseDate = "2019-01-${i % 28 +1}",
                    overview = "movie overview",
                    posterPath = "/xPihqTMhCh6b8DHYzE61jrIiNMS.jpg",
                    genreIds = listOf(878, 28, 12)
                )
            )
        }

        this.mMovieList.value = movieList
        loading.value = false

        mOriginalMovieList.clear()
        mOriginalMovieList.addAll(mMovieList.value ?: mutableListOf<MovieResult>())
    }

    private fun loadNextPageMovieListFromRemote() {
        repository.requestStoriesNextPage()
    }

    fun setErrorResponse(t: Throwable?) {
        Log.d("onFailure", "t=" + t?.localizedMessage)
        loading.value = false
    }

    fun setResponseNextPage(upcomingResult: UpcomingResult?) {
        val mergedMovieList = mutableListOf<MovieResult>()

        val currentMovieList: List<MovieResult>? = mMovieList.value
        currentMovieList?.let { mergedMovieList.addAll(currentMovieList) }
        upcomingResult?.results?.let { newPageMovieList -> mergedMovieList.addAll(newPageMovieList) }
        mergedMovieList.sortByDescending { it.releaseDate }

        mMovieList.value = mergedMovieList
        loading.value = false
        mOriginalMovieList.clear()
        mOriginalMovieList.addAll(mMovieList.value ?: mutableListOf<MovieResult>())
    }

    // improve to remote search
    fun filterMovieList(query: String) {
        val filteredList: List<MovieResult>? = if (query.isEmpty())
            mOriginalMovieList
        else {
            mOriginalMovieList.filter {
                it.title.contains(query, true)
            }
        }

        mMovieList.value = filteredList
        loading.value = false
    }
}
