package com.school.dailylife.net.service

import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * Created by chenyang
 * on 20-12-15
 */
interface SettingService {
    @Multipart
    @POST(Api.uploadContactWay)
    fun uploadContactWay(
        @Header(Api.TOKEN_STR) token: String,
        @Part part: MultipartBody.Part
    ): Observable<JsonWrapper<Any>>
}