package com.school.dailylife.net.service

import com.school.dailylife.bean.ProductDetailBean
import com.school.dailylife.config.Api
import com.school.dailylife.net.JsonWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-12-18
 */
interface ProductDetailService {

    @POST(Api.getProductDetail)
    fun getProductDetail(
        @Header(Api.TOKEN_STR) token: String,
        @Field("uid") uid: Int,
        @Field("pid") pid: Int
    ) : Observable<JsonWrapper<ProductDetailBean>>
}