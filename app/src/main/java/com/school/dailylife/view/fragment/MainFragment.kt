package com.school.dailylife.view.fragment

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.school.dailylife.R
import com.school.dailylife.bean.MainProductBean
import com.school.dailylife.config.INTENT_KEY_PRODUCT_ID
import com.school.dailylife.config.INTENT_KEY_PRODUCT_OWNER_ID
import com.school.dailylife.util.CurrentUser
import com.school.dailylife.util.loadPic
import com.school.dailylife.view.activity.ProductDetailActivity
import com.school.dailylife.view.activity.PublishActivity
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import com.school.dailylife.view.fragment.dialog.SearchResultDialog
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
        if (CurrentUser.user != null) {
            viewmodel.refresh()
        }
        initProductRecyclerview()
        initBanner()

        tv_main_search.setOnClickListener {
            val searchTar = et_main_search.text.toString().trim()
            if (!searchTar.trim().isEmpty()) {
                viewmodel.search(searchTar)
                SearchResultDialog(viewmodel).show(childFragmentManager, "searchResult")
            } else {
                Toast.makeText(requireContext(), "你输入的搜索内容不符合要求！", Toast.LENGTH_SHORT).show()
            }
        }

        srl_main_data.setOnRefreshListener {
            viewmodel.refresh()
        }

        srl_main_data.setOnLoadMoreListener {
            viewmodel.loadMore()
        }

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
            indicator = CircleIndicator(requireContext())
            setPageTransformer(DepthPageTransformer())
        }

        viewmodel.bannerData.observe {
            if (!it.isNullOrEmpty()) {
                bannerDatas.clear()
                bannerDatas.addAll(it)
                mAdapter.notifyDataSetChanged()
            }
        }


        tv_main_publish.setOnClickListener {
            startActivity(Intent(requireActivity(), PublishActivity::class.java))
        }
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
            },
            {
                val intent = Intent(requireContext(), ProductDetailActivity::class.java)
                intent.putExtra(INTENT_KEY_PRODUCT_ID, this.pid)
                intent.putExtra(INTENT_KEY_PRODUCT_OWNER_ID, this.uid)
                startActivity(intent)
            }
        )
        rv_main.adapter = adapter

        viewmodel.mainFmProductData.observe {
            if (!it.isNullOrEmpty()) {
                datas.clear()
                datas.addAll(it)
                adapter.notifyDataSetChanged()
            }

            if (srl_main_data.isRefreshing) {
                srl_main_data.finishRefresh()
            }

            if (srl_main_data.isLoading) {
                srl_main_data.finishLoadMore()
            }
        }
    }

}