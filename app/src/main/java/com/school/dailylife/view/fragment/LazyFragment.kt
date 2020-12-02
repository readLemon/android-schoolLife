package com.school.dailylife.view.fragment

/**
 * Created by chenyang
 * on 20-12-1
 */
abstract class LazyFragment : BaseFragment() {

    private var isFirstLoad = true

    override fun onResume() {
        super.onResume()
        if (isFirstLoad) {
            initData()
            initEvent()
            isFirstLoad = false
        }
    }

    open fun initEvent() = Unit


    open fun initData() = Unit
}

