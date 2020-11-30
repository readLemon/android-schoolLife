package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.school.dailylife.bean.SortBean
import com.school.dailylife.repository.SortFmRepository

/**
 * Created by chenyang
 * on 20-11-29
 */
class SortFmViewModel : BaseViewModel() {

    val repo by lazy { SortFmRepository() }
    val sortBean by lazy { MutableLiveData<SortBean>() }

    fun getSort() {
        repo.getSort()
            .subscribe(
                {
                    sortBean.value = it
                }
            )
            .lifeCycle()
    }


}