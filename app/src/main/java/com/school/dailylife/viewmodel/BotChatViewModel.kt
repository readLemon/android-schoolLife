package com.school.dailylife.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.school.dailylife.App
import com.school.dailylife.bean.ChatMsgBean
import com.school.dailylife.bean.LostSquareBean
import com.school.dailylife.bean.StuCardMessageBean
import com.school.dailylife.config.NET_REQUEST_SUCCESSFULL
import com.school.dailylife.repository.ChatRepository
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
class BotChatViewModel : BaseViewModel() {

    private val repo by lazy { ChatRepository() }

    val responseMsg = MutableLiveData<ChatMsgBean>()


    /**
     * 上传卡片进行图像识别
     *
     * @param imagePath
     * @return
     */
    fun chat(msg: String): MutableLiveData<ChatMsgBean> {


        repo.chat(msg).subscribe(
            {
                responseMsg.postValue(it)
            }, {
                Toast.makeText(App.context, "获取消息出错", Toast.LENGTH_SHORT).show()
                it.printStackTrace()
            }
        ).lifeCycle()
        return responseMsg
    }


}