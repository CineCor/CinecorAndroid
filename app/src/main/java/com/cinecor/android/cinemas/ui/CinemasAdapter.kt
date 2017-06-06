package com.cinecor.android.cinemas.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.cinecor.android.cinemas.model.Cinema
import com.cinecor.android.cinemas.listMovies.ui.ListMoviesFragment
import java.util.*

class CinemasAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var cinemas: List<Cinema> = ArrayList()

    override fun getCount(): Int {
        return cinemas.size
    }

    override fun getPageTitle(position: Int): String? {
        return cinemas[position].name
    }

    override fun getItem(position: Int): Fragment {
        return ListMoviesFragment.getInstance(position)
    }

    fun setCinemas(cinemas: List<Cinema>) {
        this.cinemas = cinemas
        notifyDataSetChanged()
    }
}
