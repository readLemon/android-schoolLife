package com.school.dailylife.view.fragment

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.school.dailylife.R
import com.school.dailylife.bean.BannerMainBean
import com.school.dailylife.bean.MainProductBean
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.transformer.DepthPageTransformer
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_rv_main.view.*

/**
 * Created by chenyang
 * on 20-11-5
 */
class MainFragment : BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_main

    private val datas = arrayListOf<MainProductBean>()


    override fun initView(view: View) {
        initProductRecyclerview()
        initBanner()
    }

    private fun initBanner() {
        val bannerDatas = listOf<BannerMainBean>(
            BannerMainBean("https://pic4.zhimg.com/v2-3be05963f5f3753a8cb75b6692154d4a_1440w.jpg?source=172ae18b")
            , BannerMainBean("https://image.lnstzy.cn/aoaodcom/2019-07/23/201907230623232853.jpg")
            , BannerMainBean("https://www.zhifure.com/upload/images/2017/9/417140648.jpg")
            , BannerMainBean("https://img95.699pic.com/photo/40007/4125.jpg_wh300.jpg")
        )
        banner_main.apply {
            addBannerLifecycleObserver(this@MainFragment)
            adapter = object : BannerImageAdapter<BannerMainBean>(bannerDatas) {
                override fun onBindView(
                    holder: BannerImageHolder,
                    data: BannerMainBean,
                    position: Int,
                    size: Int
                ) {
                    Glide.with(holder.itemView)
                        .load(data.picUrl)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                        .into(holder.imageView)
                }
            }
            setIndicator(CircleIndicator(requireContext()))
            setPageTransformer(DepthPageTransformer())
        }

    }


    private fun initProductRecyclerview() {

        for (i in 1..10) {
            datas.add(
                MainProductBean(
                    0
                    ,
                    "商品名字"
                    ,
                    200F
                    ,
                    "https://img95.699pic.com/photo/40100/6015.jpg_wh300.jpg"
                    ,
                    0
                    ,
                    "https://pic4.zhimg.com/v2-4bba972a094eb1bdc8cbbc55e2bd4ddf_1440w.jpg?source=172ae18b"
                    ,
                    "橙子"
                )
            )
        }

        val adapter = CommonRecyclerAdapter(
            R.layout.item_rv_main,
            datas,
            { bean ->
                Glide.with(this@MainFragment)
                    .load(bean.productPic)
                    .into(iv_main_rv_product_pic)
                tv_main_rv_title.text = bean.title
                tv_main_rv_price.text = "$${bean.price}"

                Glide.with(this@MainFragment)
                    .load(bean.userAvatarPic)
                    .into(civ_main_rv_avatar)
                tv_main_rv_username.text = bean.username
            }
        )
        rv_main.adapter = adapter
    }

}

