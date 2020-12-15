package com.school.dailylife.net.service

import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by chenyang
 * on 20-12-14
 */
interface ImageService {

    /**
     * 上传多张图片
     */
    @Multipart
    @POST(Api.uploadDescPhotos)
    fun uploadDescPhotos(
        @PartMap map: Map<String, RequestBody>,
        @Header(Api.TOKEN_STR) token: String
    ): Observable<JsonWrapper<Any>>





}