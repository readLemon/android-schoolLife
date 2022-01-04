package com.school.dailylife.config

/**
 * Created by chenyang
 * on 20-11-22
 */
object Api {

    const val TOKEN_STR = "token"

    const val BASE_URL = "http://1.15.139.41:18080/"

    const val login = "/login"

    const val register = "/register"

    const val getInitData = "/getInitData"

    const val search = "/getProductBySearch"

    const val mineFmData = "/getMyData"

    const val sortFmData = "/getSort"

    //用于上传描述的图片
    const val uploadDescPhotos = "/uploadProduct"

    //用于上传个人联系方式的图片
    const val uploadContactWay = "/uploadContactWay"


    const val getMyPubedProducts = "/getMyPubedProducts"

    const val getProductDetail = "/getProductDetail"
    //上传用户的个人信息
    const val postUserInformation ="/uploadUserDetail"

    const val changeProductState = "/change_state"

    //上传一卡通照片
    const val uploadStuCard = "/upload_stucard"

    const val uploadCardMessage = "/publish_card"

    const val getLostCards = "/get_losts "

    // 上传埋点信息
    const val postEvent = "/log/event "

    // 机器人交流信息
    const val chat = "/chat"
}
