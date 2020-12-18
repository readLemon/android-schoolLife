package com.school.dailylife.viewmodel

import com.school.dailylife.bean.ProductDetailBean
import com.school.dailylife.repository.ProductDetailRepository
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-12-18
 */
class ProductDetailViewModel : BaseViewModel() {

    private val repo by lazy { ProductDetailRepository() }


    fun getProductDetail(uid: Int, pid: Int): Observable<ProductDetailBean> {
        return repo.getProductDetail(uid, pid)
    }

}