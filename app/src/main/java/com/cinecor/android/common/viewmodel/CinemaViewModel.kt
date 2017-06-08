package com.cinecor.android.common.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.cinecor.android.common.model.Cinema
import com.cinecor.android.common.repository.CinemasRepository

class CinemaViewModel(repository: CinemasRepository) : ViewModel() {

    private val cinemas: LiveData<List<Cinema>> = repository.getCinemas()

    fun getCinemas(): LiveData<List<Cinema>> {
        return cinemas
    }

    fun getCinema(cinemaId: Int): LiveData<Cinema> {
        val cinema = MutableLiveData<Cinema>()
        cinema.value = cinemas.value?.find { (id) -> id == cinemaId }
        return cinema
    }
}
