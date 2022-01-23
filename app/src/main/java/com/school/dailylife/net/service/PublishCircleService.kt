package com.school.dailylife.net.service

import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-12-14
 */
interface PublishCircleService {

    /**
     * 上传圈子
     */
    @POST(Api.uploadCircle)
    fun uploadDescPhotos(
        @Header("title") title: String,
        @Header("description") description: String,
        @Body requestBody: RequestBody,
        @Header(Api.TOKEN_STR) token: String
    ): Observable<JsonWrapper<Any>>

}