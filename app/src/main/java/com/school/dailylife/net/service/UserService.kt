package com.school.dailylife.net.service

import com.school.dailylife.bean.User
import com.school.dailylife.config.Api
import com.school.dailylife.net.BaseJson
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-11-23
 */
interface UserService {

    @FormUrlEncoded
    @POST(Api.login)
    fun login(
        @Field("accountNumber") username: String,
        @Field("password") psw: String
    ): Observable<JsonWrapper<User>>


    @FormUrlEncoded
    @POST(Api.register)
    fun register(
        @Field("accountNumber") username: String,
        @Field("password") psw: String
    ): Observable<Any>

}