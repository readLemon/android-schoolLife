package com.school.dailylife.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by chenyang
 * on 2021/6/18
 */
data class ProductStateBean(
    @SerializedName("state")
    val state: Int
)
