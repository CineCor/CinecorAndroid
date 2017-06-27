package com.cinecor.android.common.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.cinecor.android.common.model.Cinema
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class CinemasRepository
@Inject constructor(val database: DatabaseReference, val firebaseAuth: FirebaseAuth) {

    fun getCinemas(): LiveData<List<Cinema>> {
        val data = MutableLiveData<List<Cinema>>()
        firebaseAuth.signInAnonymously().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                database.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        data.value = dataSnapshot.children.map { it.getValue(Cinema::class.java)!! }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.e(CinemasRepository::class.java.simpleName, databaseError.message)
                        data.value = null
                    }
                })
            } else {
                Log.e(CinemasRepository::class.java.simpleName, "Authentication failed")
                data.value = null
            }
        }
        return data
    }
}
