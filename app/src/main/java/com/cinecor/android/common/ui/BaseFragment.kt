package com.cinecor.android.common.ui

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Context
import android.support.v4.app.Fragment

import dagger.android.support.AndroidSupportInjection

open class BaseFragment : Fragment(), LifecycleRegistryOwner {

    internal var lifecycleRegistry = LifecycleRegistry(this)

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }
}
