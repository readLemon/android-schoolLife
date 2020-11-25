package com.school.dailylife.bean

/**
 * Created by chenyang
 * on 20-11-23
 */
data class User(
    val avatarPic: String,
    val nickname: String,
    val signature: String,
    val token: String
) {
    var userName: String = ""
    var psw: String = ""
}

