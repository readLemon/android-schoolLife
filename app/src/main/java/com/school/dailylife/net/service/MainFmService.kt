package com.school.dailylife.net.service

import com.school.dailylife.net.JsonWrapper
import com.school.dailylife.bean.MainDataBean
import com.school.dailylife.config.Api
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

/**
 * Created by chenyang
 * on 20-11-25
 */
interface MainFmService {


    @FormUrlEncoded
    @GET(Api.mainPageData)
    fun getMainData(
        @Field("username") username: String,
        @Field("password") psw: String
    ): Observable<JsonWrapper<MainDataBean>>


}