package com.school.dailylife.net.service

import com.school.dailylife.bean.MainDataBean
import com.school.dailylife.bean.MainProductBean
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


    @GET(Api.mainFmData)
    fun getMainData(
        @Header(Api.TOKEN_STR) token: String
    ): Observable<JsonWrapper<MainDataBean>>


    @FormUrlEncoded
    @POST(Api.search)
    fun search(
        @Header(Api.TOKEN_STR) token: String,
        @Field("searchType") searchType: String
    ): Observable<JsonWrapper<SearchResultBean>>
}