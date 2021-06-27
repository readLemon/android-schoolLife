package com.school.dailylife.repository

import com.mredrock.runtogether.network.JsonWrapperFunc
import com.school.dailylife.bean.LostSquareBean
import com.school.dailylife.bean.StuCardMessageBean
import com.school.dailylife.net.JsonWrapper
import com.school.dailylife.net.service.LostService
import com.school.dailylife.util.CurrentUser
import io.reactivex.Observable
import okhttp3.RequestBody

/**
 * Created by chenyang
 * on 2021/6/17
 */
class LostRepository : BaseRepository<LostService>() {
    override val serviceClass: Class<LostService>
        get() = LostService::class.java


    fun uploadStuCard(requestBody: RequestBody): Observable<StuCardMessageBean> {
        return observable(service.uploadStuCard(
            requestBody,
            CurrentUser.getCurrentUser().token
        )).map(JsonWrapperFunc())
    }

    fun publishCard(
        stuNumber: String,
        name: String,
        college: String,
        stuCardPic: String
    ): Observable<JsonWrapper<Any>> {
        return observable(service.publishCard(
            token = CurrentUser.getCurrentUser().token,
            stuNumber = stuNumber,
            name = name,
            college = college,
            stuCardPic = stuCardPic
        ))
    }


    fun getLostCards(index: Int): Observable<LostSquareBean> {

        return observable(service.getLostCars(index ,CurrentUser.getCurrentUser().token)).map(JsonWrapperFunc())
    }
}