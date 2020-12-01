package com.school.dailylife.repository

import com.mredrock.runtogether.network.JsonWrapperFunc
import com.school.dailylife.bean.SortBean
import com.school.dailylife.net.service.SortFmService
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-11-29
 */
class SortFmRepository : BaseRepository<SortFmService>() {

    override val serviceClass: Class<SortFmService>
        get() = SortFmService::class.java


    fun getSort(): Observable<SortBean> {
        return observable(service.getSort())
            .map(JsonWrapperFunc())
    }

}