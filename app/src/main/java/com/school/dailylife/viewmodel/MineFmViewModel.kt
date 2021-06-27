package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.school.dailylife.App
import com.school.dailylife.bean.MineFmBean
import com.school.dailylife.bean.SoledProductBean
import com.school.dailylife.repository.MineFmRepository
import com.school.dailylife.util.toast

/**
 * Created by chenyang
 * on 20-11-25
 */
class MineFmViewModel : BaseViewModel() {

    private val repo by lazy { MineFmRepository() }

    val mineBaseData by lazy { MutableLiveData<MineFmBean>() }

    val soledBeans by lazy { MutableLiveData<List<SoledProductBean.Product>>() }

    val changeStateResult by lazy { MutableLiveData<Boolean>() }

    fun getMineData() {
        repo.getMineData().subscribe(
            {
                mineBaseData.value = it
            },
            {
                it.printStackTrace()
            }
        )
            .lifeCycle()
    }

    fun getMyPubedProducts() {
        repo.getMyPubedProducts().subscribe(
            {
                soledBeans.value = it.product
            },
            {
                it.printStackTrace()
            }
        )
            .lifeCycle()
    }

    /**
     * 改变商品状态
     *
     * @param pid
     * @param targetState
     */
    fun changeProductState(pid: String, targetState: Int) {
        repo.changeProductState(
            pid = pid,
            targetState = targetState
        ).subscribe({
            App.context.toast(if (it.state != targetState) "状态改变成功！" else "状态改变失败！")
            changeStateResult.value = it.state != targetState
        }, {
            it.printStackTrace()
        }).lifeCycle()
    }

}