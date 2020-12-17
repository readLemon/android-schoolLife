package com.school.dailylife.repository

import com.school.dailylife.net.JsonWrapper
import com.school.dailylife.net.service.SettingService
import com.school.dailylife.util.CurrentUser
import io.reactivex.Observable
import okhttp3.MultipartBody

/**
 * Created by chenyang
 * on 20-12-15
 */
class SettingRepository : BaseRepository<SettingService>() {
    override val serviceClass: Class<SettingService>
        get() = SettingService::class.java

    fun uploadContactWayPhoto(phone: String, qq: String): Observable<JsonWrapper<Any>> {
        return observable(service.uploadContactWay(CurrentUser.getCurrentUser().token, phone, qq))
    }


}