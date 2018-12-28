package com.yuricfurusho.tmdbapi.ui.movielist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.yuricfurusho.model.MovieResult
import com.yuricfurusho.tmdbapi.R
import kotlinx.android.synthetic.main.movie_list_fragment.*

class MovieListFragment : Fragment(), MovieAdapter.MovieListAdapterListener {
    lateinit var mMovieAdapter: MovieAdapter

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(
            view.context,
            resources.getString(R.string.todo_go_movie_details, position + 1),
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        const val MOVIE_LIST_COLUMNS_NUMBER = 3
        fun newInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)

        swipe_movie_list.setOnRefreshListener { viewModel.loadMovieList() }

        recycler_movie_list.layoutManager = GridLayoutManager(context, MOVIE_LIST_COLUMNS_NUMBER)
        mMovieAdapter = MovieAdapter(this)
        recycler_movie_list.adapter = mMovieAdapter

        recycler_movie_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == mMovieAdapter.itemCount - 1) {
                    viewModel.loadNextPage()
                }
            }
        })

        viewModel.getEnvironment().observe(this, Observer<MovieListViewModel.ENVIRONMENT> { environment ->
            environment?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    when (environment) {
                        MovieListViewModel.ENVIRONMENT.DUMMY -> view?.foreground =
                                resources.getDrawable(R.drawable.dummy_overlay_tiled)
                        MovieListViewModel.ENVIRONMENT.REMOTE -> view?.foreground = null
                    }
                }
            }
        })

        viewModel.getLoading().observe(this, Observer<Boolean> { loading ->
            loading?.let {
                swipe_movie_list.isRefreshing = loading
            }
        })

        viewModel.getMovieList().observe(this, Observer<List<MovieResult>> { movieList ->
            movieList?.let {
                mMovieAdapter.setList(movieList)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.movies_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        menu?.setGroupVisible(R.id.environmentGroup, resources.getBoolean(R.bool.environmentOptions))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.dummyMode -> {
                viewModel.setEnvironment(MovieListViewModel.ENVIRONMENT.DUMMY)
                true
            }
            R.id.remoteMode -> {
                viewModel.setEnvironment(MovieListViewModel.ENVIRONMENT.REMOTE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
