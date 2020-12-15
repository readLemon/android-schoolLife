package com.school.dailylife.config

/**
 * Created by chenyang
 * on 20-11-22
 */
object Api {

    const val TOKEN_STR = "token"

    const val BASE_URL = "http://irving.run:18080/"

    const val login = "/login"

    const val mainFmData = "/getInitData"

    const val mineFmData = "/getMyData"

    const val sortFmData = "/getSort"

    //用于上传描述的图片
    const val uploadDescPhotos = "/uploadDescPhotos"

    //用于上传个人联系方式的图片
    const val uploadContactWay = "/uploadContactWay"


    const val getMyPubedProducts = "getMyPubedProducts"
}
