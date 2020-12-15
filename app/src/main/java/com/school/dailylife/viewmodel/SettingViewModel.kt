package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.school.dailylife.config.NET_REQUEST_SUCCESSFULL
import com.school.dailylife.repository.SettingRepository
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

    private val isUploadContactWaySuccess by lazy { MutableLiveData<Boolean>() }


    fun uploadContactWayPhoto(photoPath: String) {

        val file = File(photoPath)
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body = MultipartBody.Part.createFormData("photo", file.name, requestFile)

        repo.uploadContactWayPhoto(body).subscribe(
            {
                isUploadContactWaySuccess.value = it.status == NET_REQUEST_SUCCESSFULL
            },
            { it.printStackTrace() })
            .lifeCycle()
    }


}