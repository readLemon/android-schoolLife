package com.school.dailylife.view.fragment

import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.school.dailylife.R
import com.school.dailylife.bean.SortDetailBean
import com.school.dailylife.bean.SortTypeBean
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import com.school.dailylife.viewmodel.SortFmViewModel
import kotlinx.android.synthetic.main.fragment_sort_detail.*
import kotlinx.android.synthetic.main.item_rv_sort_detail.view.*

/**
 * Created by chenyang
 * on 20-11-22
 */
class SortDetailFragment : LazyFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_sort_detail

    private val viewmodel by viewModels<SortFmViewModel>({ requireActivity() })
    private var curPos = 0

    override fun afterViewCteated(view: View) {
        val data = mutableListOf<SortDetailBean>()
        val sortTypeBeanList = mutableListOf<SortTypeBean>()

        val adapter = CommonRecyclerAdapter(
            R.layout.item_rv_sort_detail,
            data,
            { sortTypeBean ->
                this.tv_sort_detail_name.text = sortTypeBean.name
                Glide.with(context)
                    .load(sortTypeBean.picUrl)
                    .into(this.iv_sort_detail_pic)
            },
            //当图片被点击以后
            {

            })

        rv_sort_detail.adapter = adapter

        viewmodel.sortBean.observe {
            if (it != null) {
                data.clear()
                data.addAll(it.sortTypeBeanList.get(0).sideDetailBean)
                adapter.notifyDataSetChanged()

                sortTypeBeanList.clear()
                sortTypeBeanList.addAll(it.sortTypeBeanList)
            }
        }

        viewmodel.pos.observe {
            if (it != null && it != curPos) {
                data.clear()
                data.addAll(sortTypeBeanList.get(it).sideDetailBean)
                adapter.notifyDataSetChanged()
                curPos = it
            }
        }
    }

}