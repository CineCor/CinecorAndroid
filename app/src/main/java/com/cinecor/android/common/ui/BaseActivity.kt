package com.cinecor.android.common.ui

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

open class BaseActivity : android.support.v7.app.AppCompatActivity(), android.arch.lifecycle.LifecycleRegistryOwner, dagger.android.support.HasSupportFragmentInjector {

    @javax.inject.Inject lateinit var fragmentInjector: dagger.android.DispatchingAndroidInjector<Fragment>

    private val lifecycleRegistry = android.arch.lifecycle.LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        dagger.android.AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun getLifecycle(): android.arch.lifecycle.LifecycleRegistry {
        return lifecycleRegistry
    }

    override fun supportFragmentInjector(): dagger.android.AndroidInjector<Fragment> {
        return fragmentInjector
    }
}
