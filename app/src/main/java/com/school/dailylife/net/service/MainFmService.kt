package com.school.dailylife.net.service

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
interface MainFmService {

    @FormUrlEncoded
    @POST(Api.getInitData)
    fun getMainData(
        @Header(Api.TOKEN_STR) token: String,
        @Field("index") index: Int
    ): Observable<JsonWrapper<MainDataBean>>


    @FormUrlEncoded
    @POST(Api.search)
    fun search(
        @Header(Api.TOKEN_STR) token: String,
        @Field("searchType") searchType: String
    ): Observable<JsonWrapper<SearchResultBean>>
}