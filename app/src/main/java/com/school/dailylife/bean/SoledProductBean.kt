package com.school.dailylife.bean

/**
 * Created by chenyang
 * on 20-12-15
 */
data class SoledProductBean(
    val pics: List<String>,
    val product: List<Product>
) {
    data class Product(
        val description: String,
        val price: Int,
        val status: Int,
        val title: String
    )
}

