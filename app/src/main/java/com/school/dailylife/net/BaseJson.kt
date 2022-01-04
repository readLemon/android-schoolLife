package com.school.dailylife.net

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Created by chenyang
 * on 20-11-22
 */
open class BaseJson {
   @SerializedName("info")
    var info = ""
    @SerializedName("status")
    var status = 0

    override fun toString(): String {
        return Gson().toJson(this)
    }
}