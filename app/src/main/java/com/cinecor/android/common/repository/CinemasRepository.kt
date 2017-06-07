package com.cinecor.android.common.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.cinecor.android.common.model.Cinema
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import javax.inject.Inject

class CinemasRepository
@Inject constructor(private val database: FirebaseDatabase) {

    fun getCinemas(): LiveData<List<Cinema>> {
        val data = MutableLiveData<List<Cinema>>()
        val reference = database.getReference("cinemas")
        reference.keepSynced(true)
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val cinemas = ArrayList<Cinema>()
                for (snapshot in dataSnapshot.children) {
                    cinemas.add(snapshot.getValue(Cinema::class.java))
                }
                data.value = cinemas
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(CinemasRepository::class.java.simpleName, databaseError.message)
            }
        })
        return data
    }

    fun getCinema(cinemaId: Int): LiveData<Cinema> {
        val data = MutableLiveData<Cinema>()
        val reference = database.getReference("cinemas").child(cinemaId.toString())
        reference.keepSynced(true)
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                data.value = dataSnapshot.getValue(Cinema::class.java)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(CinemasRepository::class.java.simpleName, databaseError.message)
            }
        })
        return data
    }
}
