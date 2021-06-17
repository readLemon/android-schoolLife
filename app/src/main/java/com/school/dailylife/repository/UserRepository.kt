package com.school.dailylife.repository

import com.mredrock.runtogether.network.JsonWrapperFunc
import com.school.dailylife.bean.User
import com.school.dailylife.net.JsonWrapper
import com.school.dailylife.net.service.UserService
import com.school.dailylife.util.CurrentUser
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


    fun register(username: String, psw: String): Observable<Any> {
        return observable(service.register(username, psw))
    }


    fun postUserInformation(
        sex: Int,
        age: Int,
        year: Int,
        hobby: String,
        major: String,
    ): Observable<JsonWrapper<Any>> {
        return observable(service.postUserInformation(CurrentUser.getCurrentUser().token,sex, age, year, hobby, major))
    }


}