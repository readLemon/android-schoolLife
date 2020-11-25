package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.school.dailylife.bean.MineFmBean
import com.school.dailylife.repository.MineFmRepository

/**
 * Created by chenyang
 * on 20-11-25
 */
class MineFmViewModel : BaseViewModel() {

    private val repo by lazy { MineFmRepository() }

    val mineBaseData by lazy { MutableLiveData<MineFmBean>() }

    fun getMineData() {
        repo.getMineData().subscribe(
            {
                mineBaseData.value = it
            },
            {

            }
        )
            .lifeCycle()
    }

}