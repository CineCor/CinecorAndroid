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
        firestore.collection("sessions").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                sessions.value = task.result.map { it.toObject(Session::class.java) }
            } else {
                sessions.value = null
            }
        }
    }
}
