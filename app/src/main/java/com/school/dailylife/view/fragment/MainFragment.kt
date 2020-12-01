package com.school.dailylife.view.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.school.dailylife.R
import com.school.dailylife.bean.MainProductBean
import com.school.dailylife.util.loadPic
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import com.school.dailylife.viewmodel.MainFmViewModel
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

    private val viewmodel by viewModels<MainFmViewModel>()


    override fun afterViewCteated(view: View) {
        viewmodel.getMainData()
        initProductRecyclerview()
        initBanner()
    }


    private fun initBanner() {
        val bannerDatas = mutableListOf<String>()
        val mAdapter = object : BannerImageAdapter<String>(bannerDatas) {
            override fun onBindView(
                holder: BannerImageHolder,
                data: String,
                position: Int,
                size: Int
            ) {
                Glide.with(holder.itemView)
                    .load(data)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .into(holder.imageView)
            }
        }
        banner_main.apply {
            addBannerLifecycleObserver(this@MainFragment)
            adapter = mAdapter
            setIndicator(CircleIndicator(requireContext()))
            setPageTransformer(DepthPageTransformer())
        }

        viewmodel.bannerData.observe({
            if (!it.isNullOrEmpty()) {
                bannerDatas.clear()
                bannerDatas.addAll(it)
                mAdapter.notifyDataSetChanged()
            }
        })
    }


    private fun initProductRecyclerview() {
        val datas = mutableListOf<MainProductBean>()

        val adapter = CommonRecyclerAdapter(
            R.layout.item_rv_main,
            datas,
            { bean ->
                iv_main_rv_product_pic.loadPic(bean.productPic)
                tv_main_rv_title.text = bean.title
                tv_main_rv_price.text = "$${bean.price}"

                Glide.with(this@MainFragment)
                    .load(bean.userAvatar)
                    .into(civ_main_rv_avatar)

                tv_main_rv_username.text = bean.username
            }
        )
        rv_main.adapter = adapter

        viewmodel.mainFmProductData.observe({
            if (!it.isNullOrEmpty()) {
                datas.clear()
                datas.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

}