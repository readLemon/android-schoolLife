package com.school.dailylife.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by chenyang
 * on 20-11-8
 */
class CircleProductBean(
    @SerializedName("user_name")
    val username: String,
    @SerializedName("photos")
    val photos: List<String>,
    @SerializedName("title")
    val title: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("userAvatar")
    val userAvatar: String ,
    @SerializedName("uid")
    val uid: Int,
    @SerializedName("cid")
    val cid: Int
)
