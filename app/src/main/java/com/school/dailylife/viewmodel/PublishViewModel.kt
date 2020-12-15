package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.school.dailylife.config.NET_REQUEST_SUCCESSFULL
import com.school.dailylife.repository.PublishRepository
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File

/**
 * Created by chenyang
 * on 20-12-14
 */
class PublishViewModel : BaseViewModel() {

    private val repo by lazy { PublishRepository() }
    private val isUploadPhotosSuccess by lazy { MutableLiveData<Boolean>() }


    fun uploadDescPhotos(photoPathes: List<String>) {

        val map = HashMap<String, RequestBody>()
        for (path in photoPathes) {
            val file = File(path)
            val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            map["filename=${file.name}"] = requestBody
        }

        repo.uploadDescPhotos(map).subscribe(
            {
                isUploadPhotosSuccess.value = (it.status == NET_REQUEST_SUCCESSFULL)
            },
            { it.printStackTrace() }).lifeCycle()
    }


}