package com.school.dailylife.repository

import com.mredrock.runtogether.network.JsonWrapperFunc
import com.school.dailylife.bean.ProductDetailBean
import com.school.dailylife.net.service.ProductDetailService
import com.school.dailylife.util.CurrentUser
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-12-18
 */
class ProductDetailRepository : BaseRepository<ProductDetailService>() {
    override val serviceClass: Class<ProductDetailService>
        get() = ProductDetailService::class.java


    fun getProductDetail(uid: Int, pid: Int): Observable<ProductDetailBean> {
        return observable(
            service.getProductDetail(
                CurrentUser.getCurrentUser().token,
                uid, pid
            )
        ).map(JsonWrapperFunc())
    }
}