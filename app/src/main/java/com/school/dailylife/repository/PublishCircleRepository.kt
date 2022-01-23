package com.school.dailylife.repository

import com.school.dailylife.net.JsonWrapper
import com.school.dailylife.net.service.PublishCircleService
import com.school.dailylife.net.service.PublishService
import com.school.dailylife.util.CurrentUser
import io.reactivex.Observable
import okhttp3.RequestBody

/**
 * Created by chenyang
 * on 20-12-14
 */
class PublishCircleRepository : BaseRepository<PublishCircleService>() {
    override val serviceClass: Class<PublishCircleService>
        get() = PublishCircleService::class.java

    fun uploadDescPhotos(
        title: String,
        description: String,
    requestBody: RequestBody
    ): Observable<JsonWrapper<Any>> {
        return observable(
            service.uploadDescPhotos(
                title,
                description,
                requestBody,
                CurrentUser.getCurrentUser().token
            )
        )
    }
}