package com.school.dailylife.repository

import com.google.gson.annotations.SerializedName
import com.mredrock.runtogether.network.JsonWrapperFunc
import com.school.dailylife.bean.ChatMsgBean
import com.school.dailylife.bean.MSG_TYPE
import com.school.dailylife.net.JsonWrapper
import com.school.dailylife.net.service.ChatService
import com.school.dailylife.util.CurrentUser
import io.reactivex.Observable


/**
 * Created by chenyang
 * on 20-11-25
 */
class ChatRepository : BaseRepository<ChatService>() {
    override val serviceClass: Class<ChatService>
        get() = ChatService::class.java


    fun chat(msg: String): Observable<ChatMsgBean> {
        return observable(
            service.chat(
                CurrentUser.getCurrentUser().token,
                msg
            )
        ).map { t ->
            val bean = JsonWrapper<ChatMsgBean>()
            bean.status = t.status
            bean.info = t.info
            bean.data = ChatMsgBean(MSG_TYPE.LEFT, t.data?.msg ?: "")
            bean
        }.map(JsonWrapperFunc())
    }

}

data class ChatResponseBean(
    @SerializedName("msg")
    val msg: String = ""
)