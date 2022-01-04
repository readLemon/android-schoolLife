package com.school.dailylife.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by chenyang
 * on 20-11-24
 */
data class MainDataBean(
    @SerializedName("banner")
    val banner: List<String>,
    @SerializedName("products")
    val products: List<MainProductBean>,
    @SerializedName("has_more")
    val hasMore: Boolean = false,
    @SerializedName("index")
    val index: Int = 0
)





