package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.school.dailylife.bean.MainDataBean
import com.school.dailylife.bean.MainProductBean
import com.school.dailylife.repository.MainFmRepository
import java.lang.StringBuilder

/**
 * Created by chenyang
 * on 20-11-25
 */
class MainFmViewModel : BaseViewModel() {

    private val repo by lazy { MainFmRepository() }
    private var lastMainDataBean: MainDataBean? = null

    val bannerData by lazy { MutableLiveData<List<String>>() }
    val mainFmProductData by lazy { MutableLiveData<List<MainProductBean>>() }
    val searchData by lazy { MutableLiveData<List<MainProductBean>>() }

    fun refresh() {
        getMainData(0) {
            bannerData.value = it.banner
            mainFmProductData.value = it.products
        }

    }

    fun loadMore() {
        val index = lastMainDataBean?.index ?: 0
        getMainData(index) {
            it.banner.filter { bean -> !(bannerData.value?.contains(bean) ?: false) }
                .let { listdata ->
                    val finalData = mutableListOf<String>()
                    finalData.addAll(listdata)
                    bannerData.value?.let { it1 -> finalData.addAll(it1) }
                    bannerData.value = finalData
                }
            val newData = mutableListOf<MainProductBean>()
            mainFmProductData.value?.let { it1 -> newData.addAll(it1) }
            newData.addAll(it.products)
            mainFmProductData.value = newData
            lastMainDataBean = it
        }
    }

    private fun getMainData(index: Int, onNetSuccess: (MainDataBean) -> Unit) {
        repo.getMainFmData(index).subscribe(
            {
                onNetSuccess(it)
            },
            {
                it.printStackTrace()
            })
            .lifeCycle()
    }

    fun search(searTar: String) {
        repo.search(searTar).subscribe({
            searchData.value = it.products
        }, {
            it.printStackTrace()
        })
            .lifeCycle()
    }


}