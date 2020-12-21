package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.school.dailylife.config.NET_REQUEST_SUCCESSFULL
import com.school.dailylife.repository.PublishRepository
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by chenyang
 * on 20-12-14
 */
class PublishViewModel : BaseViewModel() {

    private val repo by lazy { PublishRepository() }
    val isUploadProductSuccess by lazy { MutableLiveData<Boolean>() }

    fun uploadDescPhotos(
        price: Float,
        typeId: Int,
        categoryId: Int,
        title: String,
        desc: String,
        photoPathes: List<String>
    ) {
        val builder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("title",title)
            .addFormDataPart("description", desc)

        for (path in photoPathes) {
            val file = File(path)
            builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        }


        repo.uploadDescPhotos(price, typeId, categoryId, builder.build()).subscribe(
            {
                isUploadProductSuccess.value = (it.status == NET_REQUEST_SUCCESSFULL)
            },
            {
                it.printStackTrace()
                isUploadProductSuccess.value = false
            }).lifeCycle()
    }


}