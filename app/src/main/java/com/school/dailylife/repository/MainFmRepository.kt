package com.school.dailylife.repository

import com.mredrock.runtogether.network.JsonWrapperFunc
import com.school.dailylife.bean.MainDataBean
import com.school.dailylife.net.service.MainFmService
import com.school.dailylife.util.CurrentUser
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-11-25
 */
class MainFmRepository : BaseRepository<MainFmService>() {
    override val serviceClass: Class<MainFmService>
        get() = MainFmService::class.java


    fun getMainFmData(): Observable<MainDataBean> {
        return observable(
            service.getMainData(
                CurrentUser.getCurrentUser().username
                , CurrentUser.getCurrentUser().psw
            )
        ).map(JsonWrapperFunc())
    }


}