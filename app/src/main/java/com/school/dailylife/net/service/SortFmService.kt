package com.school.dailylife.net.service

import com.school.dailylife.bean.SortBean
import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Created by chenyang
 * on 20-11-29
 */
interface SortFmService {

    @GET(Api.sortFmData)
    fun getSort(): Observable<JsonWrapper<SortBean>>
}