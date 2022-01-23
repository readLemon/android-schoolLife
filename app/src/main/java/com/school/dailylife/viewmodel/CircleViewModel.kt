package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.school.dailylife.bean.CircleDataBean
import com.school.dailylife.bean.CircleProductBean
import com.school.dailylife.repository.CircleFmRepository

/**
 * created by chenyang.irvng@bytedance.com
 * on 2022/01/04
 */
class CircleViewModel: BaseViewModel() {

    val circleFmProductData by lazy { MutableLiveData<List<CircleProductBean>>() }
    var lastMainDataBean: CircleDataBean? = null
    private val repo by lazy { CircleFmRepository() }



    fun refresh() {
        getCircleData(0) {
            circleFmProductData.value = it.circle
        }

    }

    fun loadMore() {
        val index = lastMainDataBean?.index ?: 0
        getCircleData(index) {
            val newData = mutableListOf<CircleProductBean>()
            circleFmProductData.value?.let { it1 -> newData.addAll(it1) }
            newData.addAll(it.circle)
            circleFmProductData.value = newData
            lastMainDataBean = it
        }
    }

    private fun getCircleData(index: Int, onNetSuccess: (CircleDataBean) -> Unit) {
        repo.getCircleData(index).subscribe(
            {
                onNetSuccess(it)
            },
            {
                it.printStackTrace()
            })
            .lifeCycle()
    }
}