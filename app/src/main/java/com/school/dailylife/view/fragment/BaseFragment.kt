package com.school.dailylife.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by chenyang
 * on 20-11-5
 */
abstract class BaseFragment : Fragment() {
    @get:LayoutRes
    abstract val contentViewId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(contentViewId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
    }

    open fun initView(view: View) = Unit

    fun doNothing(event: Any) = Unit

    inline fun <T> LiveData<T>.observe(crossinline onChange: (T?) -> Unit) = observe(this@BaseFragment, Observer { onChange(it) })

    inline fun <T> LiveData<T>.observeNotNull(crossinline onChange: (T) -> Unit) = observe(this@BaseFragment, Observer {
        it ?: return@Observer
        onChange(it)
    })
}