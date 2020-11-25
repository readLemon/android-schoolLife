package com.school.dailylife.net

import com.school.dailylife.bean.BaseJson

/**
 * Created by chenyang
 * on 20-11-22
 */
class JsonWrapper<T> : BaseJson() {
    var data: T? = null
}