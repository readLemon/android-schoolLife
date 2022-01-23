package com.school.dailylife.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.school.dailylife.config.NET_REQUEST_SUCCESSFULL
import com.school.dailylife.repository.PublishCircleRepository
import com.school.dailylife.repository.PublishRepository
import com.school.dailylife.util.BitmapUtil
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.net.URLEncoder

/**
 * Created by chenyang
 * on 20-12-14
 */
class PublishCircleViewModel : BaseViewModel() {

    private val repo by lazy { PublishCircleRepository() }
    val isUploadProductSuccess by lazy { MutableLiveData<Boolean>() }

    fun uploadDescPhotos(
        title: String,
        description: String,
        photoPathes: List<String>
    ) {
        val builder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("title",  title)
            .addFormDataPart("description", description)

        for (path in photoPathes) {
            val file = File(BitmapUtil.compressImage(path))
            builder.addFormDataPart(
                "file[]",
                file.name,
                RequestBody.create(MediaType.parse("image/*"), file)
            )
        }


        repo.uploadDescPhotos(URLEncoder.encode(title, "UTF-8"), URLEncoder.encode(description, "UTF-8"), builder.build()).subscribe(
            {
                isUploadProductSuccess.value = (it.status == NET_REQUEST_SUCCESSFULL)
            },
            {
                it.printStackTrace()
                isUploadProductSuccess.value = false
            }).lifeCycle()
    }


}