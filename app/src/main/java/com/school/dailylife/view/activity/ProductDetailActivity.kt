package com.school.dailylife.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.school.dailylife.R
import com.school.dailylife.bean.ProductDetailBean
import com.school.dailylife.config.INTENT_KEY_PRODUCT_ID
import com.school.dailylife.config.INTENT_KEY_PRODUCT_OWNER_ID
import com.school.dailylife.event.AppLogUtil
import com.school.dailylife.util.loadPic
import com.school.dailylife.util.toast
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import com.school.dailylife.viewmodel.ProductDetailViewModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.item_pubing_selected_pic.view.*
import kotlinx.android.synthetic.main.item_rv_key_value.view.*

class ProductDetailActivity : BaseActivity() {
    override val contentViewId: Int
        get() = R.layout.activity_product_detail

    private val viewmodel by viewModels<ProductDetailViewModel>()
    private var disposeble: Disposable? = null
    private var uid: Int = -1
    private var pid: Int = -1

    private var ownerContactWayAdapter:
            CommonRecyclerAdapter<ProductDetailBean.OwnerContacway>? = null
    private var productPropertyAdapter:
            CommonRecyclerAdapter<ProductDetailBean.ProductProperty>? = null

    private var productPicsAdapter: CommonRecyclerAdapter<String>? = null

    private val ownerContactWays by lazy { mutableListOf<ProductDetailBean.OwnerContacway>() }
    private val productProperties by lazy { mutableListOf<ProductDetailBean.ProductProperty>() }
    private val productPics by lazy { mutableListOf<String>() }



    override fun initView(savedInstanceState: Bundle?) {
        pid = intent.getIntExtra(INTENT_KEY_PRODUCT_ID, -1)
        uid = intent.getIntExtra(INTENT_KEY_PRODUCT_OWNER_ID, -1)
        if (pid == -1
            || uid == -1
        ) {
            toast("没有收到商品id或者用户id！")
            finish()
        }
        mobEnterProductDetail(pid)

        ownerContactWayAdapter = CommonRecyclerAdapter(
            R.layout.item_rv_key_value,
            ownerContactWays,
            bindHolder = {
                this.tv_rv_key.text = it.name
                this.tv_rv_value.text = it.value
            }
        )

        productPropertyAdapter = CommonRecyclerAdapter(
            R.layout.item_rv_key_value,
            productProperties,
            bindHolder = {
                this.tv_rv_key.text = it.propertyName
                this.tv_rv_value.text = it.propertyValue
            }
        )

        productPicsAdapter = CommonRecyclerAdapter(
            R.layout.item_pubing_selected_pic,
            productPics,
            bindHolder = {
                this.iv_pubing_selected_pic.loadPic(it)
            }
        )

        rv_owner_contact_way.adapter = ownerContactWayAdapter
        rv_product_property.adapter = productPropertyAdapter
        rv_product_detail_pics.adapter = productPicsAdapter

        setSubscribe()
    }

    private fun setSubscribe() {
        disposeble = viewmodel.getProductDetail(uid, pid).subscribe(
            {
                productProperties.clear()
                productProperties.addAll(it.productPropertys)

                ownerContactWays.clear()
                ownerContactWays.addAll(it.ownerContacways)

                productPics.clear()
                productPics.addAll(it.pics)

                productPropertyAdapter?.notifyDataSetChanged()
                ownerContactWayAdapter?.notifyDataSetChanged()
                productPicsAdapter?.notifyDataSetChanged()

            },
            { it.printStackTrace() }
        )
    }

    private fun mobEnterProductDetail(productId: Int) {
        AppLogUtil.postEnterProductDetailEvent(productId)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeble?.dispose()
    }
}