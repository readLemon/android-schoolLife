package com.school.dailylife.net.service

import com.school.dailylife.bean.CircleDataBean
import com.school.dailylife.bean.MainDataBean
import com.school.dailylife.bean.SearchResultBean
import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by chenyang
 * on 20-11-25
 */
interface CircleFmService {

    @GET(Api.circle)
    fun getCircleData(
        @Header(Api.TOKEN_STR) token: String,
        @Query("index") index: Int
    ): Observable<JsonWrapper<CircleDataBean>>


}