package com.school.dailylife.net.service

import com.school.dailylife.bean.MineFmBean
import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-11-25
 */
interface MineFmService {

    @FormUrlEncoded
    @POST(Api.mineFmData)
    fun getMineData(
        @Header("token") token: String
    ): Observable<JsonWrapper<MineFmBean>>

}