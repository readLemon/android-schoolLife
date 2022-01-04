package com.school.dailylife.net.service

import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * created by chenyang.irvng@bytedance.com
 * on 2021/11/23
 */
interface AppLogService {

    @FormUrlEncoded
    @POST(Api.postEvent)
    fun postEnterProductDetailEvent(
        @Header(Api.TOKEN_STR) token: String,
        @Field("event") event: String,
        @Field("product_id") product_id: Int
    ): Observable<JsonWrapper<Any>>
}