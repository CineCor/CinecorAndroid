package com.cinecor.android.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cinecor.android.R
import com.cinecor.android.model.Session
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        viewModel.sessions.observe(this, Observer(this::showSessions))

        viewModel.fetchSessions()
    }

    private fun showSessions(sessions: List<Session>?) {
        Log.wtf("MainActivity", "Sessions: ${sessions?.size}")
    }
}
