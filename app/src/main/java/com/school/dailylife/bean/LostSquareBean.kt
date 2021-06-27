package com.school.dailylife.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by chenyang
 * on 2021/6/18
 */
data class LostSquareBean(
    @SerializedName("index")
    val index: Int = 0,
    @SerializedName("cards")
    val cards: List<StuCardMessageBean>
)
