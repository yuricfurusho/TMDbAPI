package com.yuricfurusho.repository

import com.yuricfurusho.Constants.API_KEY
import com.yuricfurusho.model.UpcomingResult
import com.yuricfurusho.tmdbapi.ui.movielist.MovieListViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListRepository(private val viewModel: MovieListViewModel) {
    private var compositeDisposable = CompositeDisposable()

    private var page: Int = 1

    fun requestStories() {
        page = 1
        val tmDbApi: TMDbApi = TMDbApi.create()

        val observableUpcomingResult: Observable<UpcomingResult> =
            tmDbApi.listUpcoming(API_KEY, "en-US", page)

        val disposable = observableUpcomingResult.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { upcomingResult: UpcomingResult? ->
                    viewModel.setResponse(upcomingResult)
                },
                { t: Throwable? ->
                    viewModel.setErrorResponse(t)
                })

        compositeDisposable.add(disposable)
    }

    fun requestStoriesNextPage() {
        val wattpadApi: TMDbApi = TMDbApi.create()

        val observableStoriesPage: Observable<UpcomingResult> =
            wattpadApi.listUpcoming(API_KEY, "en-US", ++page)

        val disposable = observableStoriesPage.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { upcomingResult: UpcomingResult? ->
                    viewModel.setResponseNextPage(upcomingResult)
                },
                { t: Throwable? ->
                    viewModel.setErrorResponse(t)
                })

        compositeDisposable.add(disposable)
    }

}