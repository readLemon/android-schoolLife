package com.school.dailylife.net.service

import com.school.dailylife.bean.LostSquareBean
import com.school.dailylife.bean.StuCardMessageBean
import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 2021/6/17
 */
interface LostService {


    /**
     * 上传一卡通图片
     */
    @POST(Api.uploadStuCard)
    fun uploadStuCard(
        @Body requestBody: RequestBody,
        @Header(Api.TOKEN_STR) token: String
    ): Observable<JsonWrapper<StuCardMessageBean>>


    /**
     * 发布失物招领卡片
     *
     * @param token
     * @param stuNumber
     * @param name
     * @param college
     * @param stuCardPic
     */
    @POST(Api.uploadCardMessage)
    fun publishCard(
        @Header(Api.TOKEN_STR) token: String,
        @Header("stu_number") stuNumber: String,
        @Body name: String,
        @Body college: String,
        @Header("stu_card_pic") stuCardPic: String
    ): Observable<JsonWrapper<Any>>


    @POST(Api.getLostCards)
    fun getLostCars(
        @Header("index") index: Int,
        @Header(Api.TOKEN_STR) token: String
    ): Observable<JsonWrapper<LostSquareBean>>

}