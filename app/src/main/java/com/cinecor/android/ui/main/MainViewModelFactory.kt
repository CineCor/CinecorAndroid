package com.cinecor.android.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory
@Inject constructor(private val firestore: FirebaseFirestore) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(firestore) as T
}
