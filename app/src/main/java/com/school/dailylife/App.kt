package com.mredrock.runtogether

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class App : Application(), ViewModelStoreOwner {
    private lateinit var mAppViewModelStore: ViewModelStore
    private lateinit var mFactory: ViewModelProvider.Factory


    override fun onCreate() {
        super.onCreate()
        context = this
        mAppViewModelStore = ViewModelStore()
        mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }

    override fun getViewModelStore() = mAppViewModelStore

    private fun getAppFactory() = mFactory

    fun getAppViewModelProvider(activity: Activity) : ViewModelProvider {
        val app = checkApplication(activity) as App
        return ViewModelProvider(app, app.getAppFactory())
    }

    fun getAppViewModelProvider(fragment: Fragment): ViewModelProvider {
        return getAppViewModelProvider(fragment.requireActivity())
    }

    private fun checkApplication(activity: Activity): Application {
        return activity.application?:throw IllegalStateException(
                "你的activity目前还没attach Application")
    }


}