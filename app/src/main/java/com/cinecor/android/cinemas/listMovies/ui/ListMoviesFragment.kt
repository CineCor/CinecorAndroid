package com.cinecor.android.cinemas.listMovies.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cinecor.android.R
import com.cinecor.android.cinemas.model.Cinema
import com.cinecor.android.cinemas.ui.CinemaViewModel
import com.cinecor.android.cinemas.ui.CinemaViewModelFactory
import com.cinecor.android.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_list_movies.*
import javax.inject.Inject

class ListMoviesFragment : BaseFragment(), Observer<Cinema> {

    companion object {
        const private val ARG_CINEMA_ID = "cinemaId"

        fun getInstance(cinemaId: Int): Fragment {
            val fragment = ListMoviesFragment()
            val args = Bundle()
            args.putInt(ARG_CINEMA_ID, cinemaId)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject lateinit var factory: CinemaViewModelFactory

    private lateinit var viewModel: CinemaViewModel
    private lateinit var adapter: ListMoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_movies, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListMoviesAdapter()
        list.setHasFixedSize(true)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity, factory).get(CinemaViewModel::class.java)
        viewModel.getCinema(arguments.getInt(ARG_CINEMA_ID, 0)).observe(this, this)
    }

    override fun onChanged(cinema: Cinema?) {
        cinema?.movies?.let { adapter.setMovies(it) }
    }
}
