package com.school.dailylife.repository

import android.icu.text.UFormat
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by chenyang
 * on 20-11-23
 */
abstract class BaseRepository {

    fun <T> observable(observable: Observable<T>) = observable.subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


//    companion object{
//        private var repo: BaseRepository ?= null
//        fun <T> getInstance():T {
//            if (repo == null) {
//
//                synchronized(this) {
//                    return if (repo == null) {
//                        repo = this.javaClass.newInstance()
//                        repo as T
//                    } else {
//                        repo as T
//                    }
//                }
//
//            }
//
//
//        }

//    }

}