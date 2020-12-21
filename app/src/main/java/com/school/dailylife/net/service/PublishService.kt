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
interface PublishService {

    /**
     * 上传我的商品
     */
    @POST(Api.uploadDescPhotos)
    fun uploadDescPhotos(
        @Header("price") price: Float,
        @Header("type_id") typeId: Int,
        @Header("category_id") categoryId: Int,
        @Body requestBody: RequestBody,
        @Header(Api.TOKEN_STR) token: String
    ): Observable<JsonWrapper<Any>>

}