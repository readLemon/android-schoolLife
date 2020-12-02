package com.school.dailylife.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by chenyang
 * on 20-11-29
 */
data class SortBean(
    val hotSearch: String,
    val messageNum: Int,
    @SerializedName("sideList") val sortTypeBeanList: List<SortTypeBean>
)



