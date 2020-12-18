package com.school.dailylife.bean

/**
 * Created by chenyang
 * on 20-12-18
 */
data class ProductDetailBean(
    val ownerContacways: List<OwnerContacway>,
    val pics: List<String>,
    val productPropertys: List<ProductProperty>
) {
    data class OwnerContacway(
        val name: String,
        val value: String
    )
    data class ProductProperty(
        val propertyName: String,
        val propertyValue: String
    )
}
