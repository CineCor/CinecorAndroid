package com.cinecor.android.moviedetail.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.cinecor.android.R
import com.cinecor.android.common.model.Movie
import com.cinecor.android.common.ui.BaseActivity
import com.cinecor.android.common.viewmodel.CinemaViewModel
import com.cinecor.android.common.viewmodel.CinemaViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_movie_detail.*
import javax.inject.Inject

class MovieDetailActivity : BaseActivity(), Observer<Movie> {
    companion object {
        const private val ARG_CINEMA_ID = "ARG_CINEMA_ID"
        const private val ARG_MOVIE_ID = "ARG_MOVIE_ID"
        fun getInstance(context: Context, cinemaId: Int?, movieId: Int?): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(ARG_CINEMA_ID, cinemaId)
            intent.putExtra(ARG_MOVIE_ID, movieId)
            return intent
        }

    }

    @Inject lateinit var factory: CinemaViewModelFactory

    private lateinit var viewModel: CinemaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setupView()
        getMovie()
    }

    private fun getMovie() {
        val cinemaId = intent.extras.getInt(ARG_CINEMA_ID, -1)
        val movieId = intent.extras.getInt(ARG_MOVIE_ID, -1)
        viewModel = ViewModelProviders.of(this, factory).get(CinemaViewModel::class.java)
        viewModel.getMovieFromCinema(movieId, cinemaId).observe(this, this)
    }

    private fun setupView() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onChanged(movie: Movie?) {
        movie?.let {
            collapsing_toolbar.title = movie.title
            overview.text = movie.overview
            backdrop.setImageURI(movie.getBackdropImages()?.first)
            movie.imdb.let {
                fab.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(movie.imdb)
                    startActivity(intent)
                }
            }
        }
    }
}
