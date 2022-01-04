package com.school.dailylife.event

import android.annotation.SuppressLint
import android.util.Log
import com.mredrock.runtogether.network.RetrofitManager
import com.school.dailylife.net.service.AppLogService
import com.school.dailylife.repository.BaseRepository
import com.school.dailylife.util.CurrentUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * created by chenyang.irvng@bytedance.com
 * on 2021/11/23
 */
object AppLogUtil {

    val service = RetrofitManager.getInstance().create(AppLogService::class.java)

    @SuppressLint("CheckResult")
    fun postEnterProductDetailEvent(productId: Int) {

        service.postEnterProductDetailEvent(
            CurrentUser.getCurrentUser().token,
            AppLogEvent.ENTER_PRODUCT_DETAIL,
            productId
        )
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                Log.d("xxxxxx", "xxxsuccess")

            }, {
                Log.d("xxxxxx", "上报埋点err：${it.message} ")
            })
    }


}