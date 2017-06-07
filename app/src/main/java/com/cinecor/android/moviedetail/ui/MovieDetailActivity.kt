package com.cinecor.android.moviedetail.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.cinecor.android.R
import com.cinecor.android.common.model.Movie
import com.cinecor.android.common.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_movie_detail.*

class MovieDetailActivity : BaseActivity() {
    companion object {
        const private val ARG_MOVIE = "ARG_MOVIE"

        fun getInstance(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(ARG_MOVIE, movie)
            return intent
        }
    }

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        movie = intent.getSerializableExtra(ARG_MOVIE) as Movie
        setupView()
    }

    private fun setupView() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

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
