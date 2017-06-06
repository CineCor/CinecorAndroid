package com.cinecor.android.cinemas.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cinecor.android.cinemas.model.CinemasRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CinemaViewModelFactory
@Inject constructor(private val repository: CinemasRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CinemaViewModel(repository) as T
    }
}
