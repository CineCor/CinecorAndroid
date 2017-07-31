package com.cinecor.android.moviedetail.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.cinecor.android.R
import com.cinecor.android.common.model.Movie
import com.cinecor.android.common.ui.BaseActivity
import com.cinecor.android.common.viewmodel.CinemaViewModel
import com.cinecor.android.common.viewmodel.CinemaViewModelFactory
import com.cinecor.android.utils.ColorUtils.rgba
import com.cinecor.android.utils.IntentUtils
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject

class MovieDetailActivity : BaseActivity(), Observer<Movie> {

    @Inject lateinit var factory: CinemaViewModelFactory

    private lateinit var viewModel: CinemaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setupView()
        getMovie()
    }

    private fun getMovie() {
        val cinemaId = intent.extras.getInt(IntentUtils.ARG_CINEMA_ID, -1)
        val movieId = intent.extras.getInt(IntentUtils.ARG_MOVIE_ID, -1)
        viewModel = ViewModelProviders.of(this, factory).get(CinemaViewModel::class.java)
        viewModel.getMovieFromCinema(movieId, cinemaId).observe(this, this)
    }

    private fun setupView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun showMovie(movie: Movie) {
        val mainColor = movie.colors[Movie.Colors.MAIN.toString()]?.rgba()
        val textColor = movie.colors[Movie.Colors.TITLE.toString()]?.rgba()
        val backdropImages = movie.getBackdropImages()
        val posterImages = movie.getPosterImages()

        name.text = movie.title
        genres.text = movie.genres?.joinToString(", ")
        overview.text = movie.overview

        mainColor?.let { banner.setBackgroundColor(it) }
        textColor?.let {
            genres.setTextColor(it)
            name.setTextColor(it)
        }

        Glide.with(this)
                .load(backdropImages?.first)
                .thumbnail(Glide.with(this).load(backdropImages?.second))
                .into(backdrop)

        Glide.with(this)
                .load(posterImages?.first)
                .thumbnail(Glide.with(this).load(posterImages?.second))
                .into(poster)
    }

    override fun onChanged(movie: Movie?) {
        movie?.let { showMovie(movie) }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
