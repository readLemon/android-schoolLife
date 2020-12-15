package com.school.dailylife.view.activity

import android.os.Bundle
import com.school.dailylife.R
import com.school.dailylife.config.INTENT_KEY_PRODUCT_ID
import com.school.dailylife.config.INTENT_KEY_PRODUCT_OWNER_ID
import com.school.dailylife.util.toast

class ProductDetailActivity : BaseActivity() {
    override val contentViewId: Int
        get() = R.layout.activity_product_detail

    override fun onStart() {
        super.onStart()
        if (intent.getIntExtra(INTENT_KEY_PRODUCT_ID, -1) == -1
            || intent.getIntExtra(INTENT_KEY_PRODUCT_OWNER_ID, -1) == -1
        ) {
            toast("没有收到商品id或者用户id！")
            finish()
        }
    }


    override fun initView(savedInstanceState: Bundle?) {



    }
}