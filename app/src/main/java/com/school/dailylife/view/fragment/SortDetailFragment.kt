package com.school.dailylife.view.fragment

import android.view.View
import com.bumptech.glide.Glide
import com.school.dailylife.R
import com.school.dailylife.bean.SortDetailBean
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_sort_detail.*
import kotlinx.android.synthetic.main.item_rv_sort_detail.view.*

/**
 * Created by chenyang
 * on 20-11-22
 */
class SortDetailFragment : BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_sort_detail


    override fun initView(view: View) {
        val data = mutableListOf<SortDetailBean>()
        for (i in 1..15) {
            data.add(SortDetailBean("食品","https://pic4.zhimg.com/v2-4bba972a094eb1bdc8cbbc55e2bd4ddf_1440w.jpg"));
        }

//https://pic4.zhimg.com/v2-4bba972a094eb1bdc8cbbc55e2bd4ddf_1440w.jpg
        val adapter = CommonRecyclerAdapter(R.layout.item_rv_sort_detail
        ,data
        ,{sortTypeBean ->
                this.tv_sort_detail_name.text = sortTypeBean.typename
                Glide.with(context)
                    .load(sortTypeBean.picUrl)
                    .into(this.iv_sort_detail_pic)
            })

        rv_sort_detail.adapter = adapter

    }


}