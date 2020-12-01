package com.school.dailylife.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by chenyang
 * on 20-11-22
 */
data class SortTypeBean(
    val id: Int,
    val type: String,
    @SerializedName("sideDetail")
    val sideDetailBean: List<SortDetailBean>
)