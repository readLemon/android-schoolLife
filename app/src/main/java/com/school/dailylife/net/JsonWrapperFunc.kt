package com.mredrock.runtogether.network

import android.util.Log
import com.school.dailylife.net.JsonWrapper
import com.school.dailylife.config.DEFAULT_FAULT_INFO
import com.school.dailylife.net.Fault
import io.reactivex.functions.Function

/**
 * Created by chenyang
 * on 20-11-22
 */
class JsonWrapperFunc<T> : Function<JsonWrapper<T>, T> {
    override fun apply(t: JsonWrapper<T>): T {
        if (t.status != 200) {
            throw Fault(t.status, t.info)
        }

        if (t.data == null) {
            Log.d("JsonWrapperFunc", "the response data is null")
            throw Fault(DEFAULT_FAULT_INFO,"the response data is null")
        }

        return t.data as T
    }
}