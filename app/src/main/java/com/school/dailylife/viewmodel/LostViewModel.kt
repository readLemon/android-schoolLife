package com.school.dailylife.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.school.dailylife.bean.LostSquareBean
import com.school.dailylife.bean.StuCardMessageBean
import com.school.dailylife.config.NET_REQUEST_SUCCESSFULL
import com.school.dailylife.repository.LostRepository
import com.school.dailylife.util.BitmapUtil
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by chenyang
 * on 2021/6/17
 */
class LostViewModel : BaseViewModel() {

    private val repo by lazy { LostRepository() }

    val uploadCardResult by lazy { MutableLiveData<StuCardMessageBean>() }

    val publishResult by lazy { MutableLiveData<Boolean>() }

    val mLostCards by lazy { MutableLiveData<LostSquareBean>() }


    /**
     * 上传卡片进行图像识别
     *
     * @param imagePath
     * @return
     */
    fun uploadStuCard(imagePath: String): MutableLiveData<StuCardMessageBean> {

        val builder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)

        val file = File(BitmapUtil.compressImage(imagePath))
        builder.addFormDataPart(
            "file",
            file.name,
            RequestBody.create(MediaType.parse("image/*"), file)
        )

        repo.uploadStuCard(builder.build()).subscribe(
            {
                uploadCardResult.postValue(it)
            }, {
                it.printStackTrace()
            }
        ).lifeCycle()
        return uploadCardResult
    }

    /**
     * 发布卡片失物招领
     *
     * @param stuNumber
     * @param name
     * @param college
     * @param stuCardPic
     */
    fun publishCard(
        stuNumber: String,
        name: String,
        college: String,
        stuCardPic: String
    ): MutableLiveData<Boolean> {
        repo.publishCard(stuNumber, name, college, stuCardPic).subscribe(
            {
                publishResult.value = it.status == NET_REQUEST_SUCCESSFULL
                Log.d(TAG, "发布成功")
            }, {
                it.printStackTrace()
                publishResult.value = false
                Log.d(TAG, "发布失败")
            }
        ).lifeCycle()

        return publishResult
    }

    /**
     * 获取
     *
     */
    fun getLostCards(index: Int): MutableLiveData<LostSquareBean> {
        repo.getLostCards(index).subscribe(
            {
                mLostCards.value = it
            },
            {
                it.printStackTrace()
            }
        ).lifeCycle()
        return mLostCards
    }


    companion object {
        private val TAG = "LostViewModel"
    }
}