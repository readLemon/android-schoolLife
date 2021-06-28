package com.school.dailylife.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by chenyang
 * on 2021/6/27
 */
data class LostPickerBean(
    @SerializedName("stuNumber")
    val stuNumber: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("college")
    val college: String = "",
    @SerializedName("pickName")
    val pickName: String = "",
    @SerializedName("pickQQ")
    val pickQQ: String = "",
    @SerializedName("pickTel")
    val pickTel: String = "",
)