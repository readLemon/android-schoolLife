package com.school.dailylife.net.service

import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import com.school.dailylife.repository.ChatResponseBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * created by chenyang.irvng@bytedance.com
 * on 2021/11/23
 */
interface ChatService {

    @FormUrlEncoded
    @POST(Api.chat)
    fun chat(
        @Header(Api.TOKEN_STR) token: String,
        @Field("msg") msg: String
    ): Observable<JsonWrapper<ChatResponseBean>>
}