package com.school.dailylife.net

import com.google.gson.annotations.SerializedName

/**
 * Created by chenyang
 * on 20-11-22
 */
class JsonWrapper<T> : BaseJson() {
    @SerializedName("data")
    var data: T? = null
}