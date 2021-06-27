package com.school.dailylife.net.service

import com.school.dailylife.bean.MineFmBean
import com.school.dailylife.bean.ProductStateBean
import com.school.dailylife.bean.SoledProductBean
import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-11-25
 */
interface MineFmService {

    @POST(Api.mineFmData)
    fun getMineData(
        @Header(Api.TOKEN_STR) token: String
    ): Observable<JsonWrapper<MineFmBean>>


    @GET(Api.getMyPubedProducts)
    fun getMyPubedProducts(
        @Header(Api.TOKEN_STR) token: String
    ): Observable<JsonWrapper<SoledProductBean>>

    @POST(Api.changeProductState)
    fun changeProductState(
        @Header(Api.TOKEN_STR) token: String,
        @Header("pid") pid: String,
        @Header("target_state") targetState: Int
    ): Observable<JsonWrapper<ProductStateBean>>

}