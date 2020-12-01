package com.school.dailylife.net.service

import com.school.dailylife.bean.SortBean
import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-11-29
 */
interface SortFmService {

    @POST(Api.sortFmData)
    fun getSort(): Observable<JsonWrapper<SortBean>>
}