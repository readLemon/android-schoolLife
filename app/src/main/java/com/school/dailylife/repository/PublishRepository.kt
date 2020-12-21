package com.school.dailylife.repository

import com.school.dailylife.net.JsonWrapper
import com.school.dailylife.net.service.PublishService
import com.school.dailylife.util.CurrentUser
import io.reactivex.Observable
import okhttp3.RequestBody

/**
 * Created by chenyang
 * on 20-12-14
 */
class PublishRepository : BaseRepository<PublishService>() {
    override val serviceClass: Class<PublishService>
        get() = PublishService::class.java

    fun uploadDescPhotos(
        price: Float,
        typeId: Int,
        categoryId: Int,
    requestBody: RequestBody
    ): Observable<JsonWrapper<Any>> {
        return observable(
            service.uploadDescPhotos(
                price,
                typeId,
                categoryId,
                requestBody,
                CurrentUser.getCurrentUser().token
            )
        )
    }
}