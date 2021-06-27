package com.school.dailylife.bean

import com.google.gson.annotations.SerializedName


/**
 * Created by chenyang
 * on 2021/6/17
 */
data class StuCardMessageBean(
    @SerializedName("status")
    val status: Int = 1,
    @SerializedName("stu_number")
    val stuNumber: String = "",//学号
    @SerializedName("name")
    val name: String = "",//名字
    @SerializedName("college")
    val college: String = "",//学院
    @SerializedName("stu_card_pic")
    val cardPic: String = "" //学生卡的链接
)
