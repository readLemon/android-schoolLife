package com.school.dailylife.repository

import com.mredrock.runtogether.network.JsonWrapperFunc
import com.school.dailylife.bean.MineFmBean
import com.school.dailylife.net.service.MineFmService
import com.school.dailylife.util.CurrentUser
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-11-25
 */
class MineFmRepository : BaseRepository<MineFmService>() {
    override val serviceClass: Class<MineFmService>
        get() = MineFmService::class.java

    fun getMineData(): Observable<MineFmBean> {

        return observable(
            service.getMineData(
                CurrentUser.getCurrentUser().token
            )
        ).map(JsonWrapperFunc<MineFmBean>())


    }
}