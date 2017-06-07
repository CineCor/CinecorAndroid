package com.cinecor.android.common.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

import com.cinecor.android.common.model.Cinema
import com.cinecor.android.common.repository.CinemasRepository

class CinemaViewModel(private val repository: CinemasRepository) : ViewModel() {

    val cinemas: LiveData<List<Cinema>>

    init {
        cinemas = repository.getCinemas()
    }

    fun getCinema(cinemaId: Int): LiveData<Cinema> {
        return repository.getCinema(cinemaId)
    }
}