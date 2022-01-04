package com.school.dailylife.view.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by chenyang
 * on 20-11-5
 */
abstract class BaseActivity : AppCompatActivity() {
    @get:LayoutRes
    protected abstract val contentViewId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetContentViewId(savedInstanceState)
        if (setContentView()) setContentView(contentViewId)
        observeVM()
        initView(savedInstanceState)
    }

    protected open fun initView(savedInstanceState: Bundle?) = Unit
    protected open fun observeVM() = Unit

    protected open fun beforeSetContentViewId(savedInstanceState: Bundle?) = Unit


    protected open fun setContentView() = true

    fun doNothing(event: Any) = Unit

    inline fun <T> LiveData<T>.observe(crossinline onChange: (T?) -> Unit) =
        observe(this@BaseActivity, Observer { onChange(it) })

    inline fun <T> LiveData<T>.observeNotNull(crossinline onChange: (T) -> Unit) =
        observe(this@BaseActivity, Observer {
            it ?: return@Observer
            onChange(it)
        })
}
