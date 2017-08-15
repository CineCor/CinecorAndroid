package com.cinecor.android.data.source.repository

import android.arch.lifecycle.LiveData
import com.cinecor.android.data.model.Cinema

interface CinemasRepository {

    fun getCinemas(): LiveData<List<Cinema>>

}
