package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.school.dailylife.config.NET_REQUEST_SUCCESSFULL
import com.school.dailylife.net.JsonWrapper
import com.school.dailylife.repository.SettingRepository
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by chenyang
 * on 20-12-15
 */
class SettingViewModel : BaseViewModel() {

    private val repo by lazy { SettingRepository() }



    fun uploadContactWayPhoto(phone: String, qq: String): Observable<JsonWrapper<Any>> {

        return repo.uploadContactWayPhoto(phone, qq)
    }


}