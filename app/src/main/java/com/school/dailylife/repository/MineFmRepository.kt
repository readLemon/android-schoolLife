package com.school.dailylife.repository

import com.mredrock.runtogether.network.JsonWrapperFunc
import com.school.dailylife.bean.MineFmBean
import com.school.dailylife.bean.ProductStateBean
import com.school.dailylife.bean.SoledProductBean
import com.school.dailylife.net.JsonWrapper
import com.school.dailylife.net.service.MineFmService
import com.school.dailylife.util.CurrentUser
import io.reactivex.Observable
import java.lang.StringBuilder

/**
 * Created by chenyang
 * on 20-11-25
 */
class MineFmRepository : BaseRepository<MineFmService>() {
    override val serviceClass: Class<MineFmService>
        get() = MineFmService::class.java

    fun getMineData(): Observable<MineFmBean> {

        return observable(
            service.getMineData(
                CurrentUser.getCurrentUser().token
            )
        ).map(JsonWrapperFunc())
    }

    fun getMyPubedProducts(): Observable<SoledProductBean> {
        return observable(service.getMyPubedProducts(CurrentUser.getCurrentUser().token))
            .map(JsonWrapperFunc())
    }


    fun changeProductState(pid: String, targetState: Int): Observable<ProductStateBean> {
        return observable(service.changeProductState(
            token = CurrentUser.getCurrentUser().token,
            pid = pid,
            targetState = targetState
        )).map(JsonWrapperFunc())
    }

}