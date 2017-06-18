package com.cinecor.android.cinemas.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import com.cinecor.android.R
import com.cinecor.android.common.model.Cinema
import com.cinecor.android.common.ui.BaseActivity
import com.cinecor.android.common.viewmodel.CinemaViewModel
import com.cinecor.android.common.viewmodel.CinemaViewModelFactory
import kotlinx.android.synthetic.main.activity_cinemas.*
import javax.inject.Inject

class CinemasActivity : BaseActivity(), Observer<List<Cinema>> {

    @Inject lateinit var factory: CinemaViewModelFactory
    @Inject lateinit var adapter: CinemasAdapter

    private lateinit var viewModel: CinemaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.CinecorTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinemas)
        setupView()
        getCinemas()
    }

    private fun setupView() {
        setSupportActionBar(toolbar)
        pager.offscreenPageLimit = 2
        pager.adapter = adapter
    }

    private fun getCinemas() {
        viewModel = ViewModelProviders.of(this, factory).get(CinemaViewModel::class.java)
        viewModel.getCinemas().observe(this, this)
    }

    override fun onChanged(cinemas: List<Cinema>?) {
        if (cinemas != null) {
            loader.visibility = GONE
            adapter.setCinemas(cinemas)
        } else {
            Toast.makeText(this, "There was an error loading the cinemas", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
