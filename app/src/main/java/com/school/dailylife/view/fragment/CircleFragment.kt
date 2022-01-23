package com.school.dailylife.view.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.school.dailylife.R
import com.school.dailylife.bean.CircleProductBean
import com.school.dailylife.config.INTENT_KEY_PRODUCT_ID
import com.school.dailylife.config.INTENT_KEY_PRODUCT_OWNER_ID
import com.school.dailylife.util.loadPic
import com.school.dailylife.view.activity.ProductDetailActivity
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import com.school.dailylife.viewmodel.CircleViewModel
import kotlinx.android.synthetic.main.fragment_circle.*
import kotlinx.android.synthetic.main.image_of_circle.view.*
import kotlinx.android.synthetic.main.item_rv_circle.*
import kotlinx.android.synthetic.main.item_rv_circle.view.*

/**
 * created by chenyang.irvng@bytedance.com
 * on 2022/01/04
 */
class CircleFragment : LazyFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_circle

    private val viewmodel by viewModels<CircleViewModel>()


    override fun initData() {
        viewmodel.refresh()

    }

    override fun afterViewCteated(view: View) {
        initProductRecyclerview()
        srl_circle_data.setOnRefreshListener {
            viewmodel.refresh()
        }

        srl_circle_data.setOnLoadMoreListener {
            if (viewmodel.lastMainDataBean?.hasMore != false) {
                viewmodel.loadMore()
            } else {
                Toast.makeText(requireContext(), "没有更多圈子了", Toast.LENGTH_SHORT).show()
                it.finishLoadMore()
            }
        }
    }

    private fun initProductRecyclerview() {
        val datas = mutableListOf<CircleProductBean>()

        val adapter = CommonRecyclerAdapter(
            R.layout.item_rv_circle,
            datas,
            { bean ->
                this.tv_circle_rv_username.text = bean.username
                this.tv_circle_rv_title.text = "标题：${bean.title}"
                this.tv_circle_rv_detail.text = bean.detail

                Glide.with(this@CircleFragment)
                    .load(bean.userAvatar)
                    .into(civ_circle_rv_avatar)

                for (tmp in bean.photos) {
                    val realPath = tmp.replace("irving.cloud", "1.15.139.41", false)
                    val image = layoutInflater.inflate(R.layout.image_of_circle, null, false)
                    image.iv_circle_detail_pic.loadPic(realPath)
                    this.ll_circle_photos.addView(image)
                }

            },
            {
//                val intent = Intent(requireContext(), ProductDetailActivity::class.java)
//                intent.putExtra(INTENT_KEY_PRODUCT_ID, this.pid)
//                intent.putExtra(INTENT_KEY_PRODUCT_OWNER_ID, this.uid)
//                startActivity(intent)
            }
        )
        rv_circle.adapter = adapter

        viewmodel.circleFmProductData.observe {
            if (!it.isNullOrEmpty()) {
                datas.clear()
                datas.addAll(it)
                adapter.notifyDataSetChanged()
            }

            if (srl_circle_data.isRefreshing) {
                srl_circle_data.finishRefresh()
            }

            if (srl_circle_data.isLoading) {
                srl_circle_data.finishLoadMore()
            }
        }
    }


}