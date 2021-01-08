package com.school.dailylife.repository

import com.mredrock.runtogether.network.JsonWrapperFunc
import com.school.dailylife.bean.MainDataBean
import com.school.dailylife.bean.MainProductBean
import com.school.dailylife.bean.SearchResultBean
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
                CurrentUser.getCurrentUser().token
//            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiaWF0IjoxNjA2ODAyODE5fQ.l4L7lIr3Yg5na2pCsY15ggoUxEzfgLcGnel87nT6h7I"
            )
        ).map(JsonWrapperFunc())
    }


    fun search(searchTar: String): Observable<SearchResultBean> {
        return observable(service.search(CurrentUser.getCurrentUser().token, searchTar)).map(
            JsonWrapperFunc()
        )
    }

}