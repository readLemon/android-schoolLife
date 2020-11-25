package com.school.dailylife.net

import com.google.gson.Gson

/**
 * Created by chenyang
 * on 20-11-22
 */
open class BaseJson {
    var info = ""
    var status = 0

    override fun toString(): String {
        return Gson().toJson(this)
    }
}