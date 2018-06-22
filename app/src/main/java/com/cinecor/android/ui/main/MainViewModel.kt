package com.cinecor.android.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cinecor.android.model.Session
import com.google.firebase.firestore.FirebaseFirestore

class MainViewModel(
        private val firestore: FirebaseFirestore
) : ViewModel() {

    val sessions = MutableLiveData<List<Session>>()

    fun fetchSessions() {

    }
}
