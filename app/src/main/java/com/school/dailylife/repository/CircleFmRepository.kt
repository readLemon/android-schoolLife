package com.school.dailylife.repository

import com.mredrock.runtogether.network.JsonWrapperFunc
import com.school.dailylife.bean.CircleDataBean
import com.school.dailylife.net.service.CircleFmService
import com.school.dailylife.util.CurrentUser
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-11-25
 */
class CircleFmRepository : BaseRepository<CircleFmService>() {
    override val serviceClass: Class<CircleFmService>
        get() = CircleFmService::class.java


    fun getCircleData(index: Int): Observable<CircleDataBean> {
        return observable(
            service.getCircleData(
//            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiaWF0IjoxNjA2ODAyODE5fQ.l4L7lIr3Yg5na2pCsY15ggoUxEzfgLcGnel87nT6h7I"
                CurrentUser.getCurrentUser().token,
                index
            )
        ).map(JsonWrapperFunc())
    }


}