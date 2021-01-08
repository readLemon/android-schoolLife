package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.school.dailylife.bean.MainProductBean
import com.school.dailylife.repository.MainFmRepository
import java.lang.StringBuilder

/**
 * Created by chenyang
 * on 20-11-25
 */
class MainFmViewModel : BaseViewModel() {

    private val repo by lazy { MainFmRepository() }

    val bannerData by lazy { MutableLiveData<List<String>>() }
    val mainFmProductData by lazy { MutableLiveData<List<MainProductBean>>() }
    val searchData by lazy { MutableLiveData<List<MainProductBean>>() }

    fun getMainData() {
        repo.getMainFmData().subscribe(
            {
                bannerData.value = it.banner
                mainFmProductData.value = it.products
            },
            {
                it.printStackTrace()
            })
            .lifeCycle()
    }

    fun search(searTar: String) {
        repo.search(searTar).subscribe({
            searchData.value = it.products
        },{
            it.printStackTrace()
        })
            .lifeCycle()
    }


}