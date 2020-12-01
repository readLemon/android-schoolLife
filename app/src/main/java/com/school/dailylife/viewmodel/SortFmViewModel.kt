package com.school.dailylife.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.school.dailylife.bean.SortBean
import com.school.dailylife.repository.SortFmRepository

/**
 * Created by chenyang
 * on 20-11-29
 */
class SortFmViewModel : BaseViewModel() {

    private val repo by lazy { SortFmRepository() }

    val sortBean by lazy { MutableLiveData<SortBean>() }
//    val sortBean = liveData<SortBean> {
//        repo.getSort().subscribe({
//            emit(it)
//        }, CommonConsumer())
//    }

    fun getSort() {
        repo.getSort()
            .subscribe(
                {
                    sortBean.value = it
                },
                {
                    it.printStackTrace()
                }
            )
            .lifeCycle()
    }


}