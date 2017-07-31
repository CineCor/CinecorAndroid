package com.cinecor.android.common.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.cinecor.android.common.model.Cinema
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn
import javax.inject.Inject

class CinemasRepository
@Inject constructor(val firebaseAuth: FirebaseAuth, val database: FirebaseDatabase, val log: AnkoLogger) {

    fun getCinemas(): LiveData<List<Cinema>> {
        val data = MutableLiveData<List<Cinema>>()
        firebaseAuth.signInAnonymously().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reference = database.getReference("cinemas")
                reference.keepSynced(true)
                reference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        data.value = dataSnapshot.children.map { it.getValue(Cinema::class.java)!! }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        log.warn { databaseError.message }
                        data.value = null
                    }
                })
            } else {
                log.warn { "Authentication failed" }
                data.value = null
            }
        }
        return data
    }
}
