package com.school.dailylife.repository

import com.mredrock.runtogether.network.JsonWrapperFunc
import com.school.dailylife.bean.User
import com.school.dailylife.net.BaseJson
import com.school.dailylife.net.service.UserService
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-11-23
 */
class UserRepository : BaseRepository<UserService>() {

    override val serviceClass: Class<UserService>
        get() = UserService::class.java

    fun login(username: String, psw: String): Observable<User> {
        return observable(service.login(username, psw)).map(JsonWrapperFunc<User>())
    }


    fun register(username: String, psw: String): Observable<BaseJson> {
        return observable(service.register(username, psw)).map(JsonWrapperFunc<BaseJson>())
    }


}