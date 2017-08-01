package com.cinecor.android.cinemas.movies.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.cinecor.android.R
import com.cinecor.android.common.model.Cinema
import com.cinecor.android.common.model.Movie
import com.cinecor.android.common.ui.BaseFragment
import com.cinecor.android.common.viewmodel.CinemaViewModel
import com.cinecor.android.common.viewmodel.CinemaViewModelFactory
import com.cinecor.android.moviedetail.ui.MovieDetailActivity
import com.cinecor.android.utils.IntentUtils
import kotlinx.android.synthetic.main.fragment_list_movies.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject


class MoviesFragment : BaseFragment(), Observer<Cinema>, MoviesAdapter.OnMovieClickListener {

    companion object {
        @JvmStatic fun getInstance(cinemaId: Int?): Fragment {
            val fragment = MoviesFragment()
            val args = Bundle()
            args.putInt(IntentUtils.ARG_CINEMA_ID, cinemaId ?: -1)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject lateinit var factory: CinemaViewModelFactory
    @Inject lateinit var adapter: MoviesAdapter

    private lateinit var viewModel: CinemaViewModel
    private var cinemaId = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_movies, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.setHasFixedSize(true)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cinemaId = arguments.getInt(IntentUtils.ARG_CINEMA_ID, -1)
        viewModel = ViewModelProviders.of(activity, factory).get(CinemaViewModel::class.java)
        viewModel.getCinema(cinemaId).observe(this, this)
    }

    override fun onChanged(cinema: Cinema?) {
        cinema?.movies?.let { adapter.updateMovies(it) } ?: toast("Error loading Cinemas")
    }

    override fun onMovieClicked(movie: Movie, image: ImageView) {
        val intent = activity.intentFor<MovieDetailActivity>(IntentUtils.ARG_CINEMA_ID to cinemaId, IntentUtils.ARG_MOVIE_ID to movie.id)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, image, "backdrop").toBundle()
        startActivity(intent, options)
    }
}
