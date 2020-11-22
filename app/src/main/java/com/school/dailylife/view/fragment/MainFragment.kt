package com.school.dailylife.view.fragment

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.school.dailylife.R
import com.school.dailylife.bean.MainRecyclerItemBean
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_rv_main.view.*

/**
 * Created by chenyang
 * on 20-11-5
 */
class MainFragment : BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_main
    private val datas = arrayListOf<MainRecyclerItemBean>()

    override fun initView(view: View) {
        initProductRecyclerview()

    }

    private fun initProductRecyclerview() {

        for (i in 1..10) {
            datas.add(MainRecyclerItemBean("https://img95.699pic.com/photo/40100/6015.jpg_wh300.jpg"
            , "商品名字"
            , "$200"
            , "https://pic4.zhimg.com/v2-4bba972a094eb1bdc8cbbc55e2bd4ddf_1440w.jpg?source=172ae18b"
            , "橙子"))
        }

        val adapter = CommonRecyclerAdapter(
            R.layout.item_rv_main,
            datas,
            {   bean ->
                Glide.with(this@MainFragment)
                    .load(bean.productPic)
                    .into(iv_main_rv_product_pic)
                tv_main_rv_title.text = bean.title
                tv_main_rv_price.text =bean.price

                Glide.with(this@MainFragment)
                    .load(bean.avatarUrl)
                    .into(civ_main_rv_avatar)
                tv_main_rv_username.text = bean.username
            }
        )
        rv_main.adapter = adapter


    }
}