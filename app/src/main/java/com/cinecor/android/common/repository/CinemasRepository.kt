package com.cinecor.android.common.repository

import android.arch.lifecycle.LiveData
import com.cinecor.android.common.model.Cinema

interface CinemasRepository {

    fun getCinemas(): LiveData<List<Cinema>>

}
