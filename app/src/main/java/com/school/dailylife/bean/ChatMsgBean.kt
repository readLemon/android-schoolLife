package com.school.dailylife.bean

/**
 * created by chenyang.irvng@bytedance.com
 * on 2021/11/23
 */
data class ChatMsgBean(
    val msgPosition: MSG_TYPE,
    val msg: String
)

enum class MSG_TYPE {
    LEFT, RIGHT
}