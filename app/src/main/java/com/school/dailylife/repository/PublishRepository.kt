package com.school.dailylife.repository

import com.school.dailylife.net.JsonWrapper
import com.school.dailylife.net.service.ImageService
import com.school.dailylife.util.CurrentUser
import io.reactivex.Observable
import okhttp3.RequestBody

/**
 * Created by chenyang
 * on 20-12-14
 */
class PublishRepository : BaseRepository<ImageService>() {
    override val serviceClass: Class<ImageService>
        get() = ImageService::class.java


    fun uploadDescPhotos(
        typeId: Int,
        categoryId: Int,
        title: String,
        desc: String,
        map: Map<String, RequestBody>
    ): Observable<JsonWrapper<Any>> {
        return observable(
            service.uploadDescPhotos(
                typeId,
                categoryId,
                title,
                desc,
                map,
                CurrentUser.getCurrentUser().token
            )
        )
    }


}