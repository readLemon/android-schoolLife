package com.school.dailylife.net.service

import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

/**
 * Created by chenyang
 * on 20-12-15
 */
interface SettingService {
    @Multipart
    @POST(Api.uploadContactWay)
    fun uploadContactWay(
        @Header(Api.TOKEN_STR) token: String,
        @Field("tel") phone: String,
        @Field("qq") qq: String
    ): Observable<JsonWrapper<Any>>
}