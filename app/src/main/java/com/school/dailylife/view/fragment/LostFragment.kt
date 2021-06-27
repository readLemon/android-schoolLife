package com.school.dailylife.view.fragment

import android.content.Intent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.school.dailylife.R
import com.school.dailylife.bean.LostSquareBean
import com.school.dailylife.bean.StuCardMessageBean
import com.school.dailylife.view.activity.PublishStuCardActivity
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import com.school.dailylife.viewmodel.LostViewModel
import kotlinx.android.synthetic.main.fragment_lost.*
import kotlinx.android.synthetic.main.item_rv_losts.view.*

/**
 * Created by chenyang
 * on 2021/6/17
 */
class LostFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {
    override val contentViewId: Int
        get() = R.layout.fragment_lost

    private val viewModel by viewModels<LostViewModel>()
    private var adapter: CommonRecyclerAdapter<StuCardMessageBean>? = null
    private val lostData by lazy { mutableListOf<StuCardMessageBean>() }
    private var nextIndex = 0
    private val mObserver by lazy {
        Observer<LostSquareBean> { t ->
            t?.apply {
                nextIndex = this.index
                lostData.addAll(t.cards)
                adapter?.notifyDataSetChanged()
                sr_refresh.isRefreshing = false
            }
        }
    }


    override fun afterViewCteated(view: View) {

        setLostRecyc()
        btn_upload_card.setOnClickListener {
            startActivity(Intent(requireContext(), PublishStuCardActivity::class.java))
        }
        sr_refresh.setOnRefreshListener(this)
        fab_refresh.setOnClickListener {loadMoreData()}

    }

    /**
     * 设置失物招领的广场的recyc
     *
     */
    private fun setLostRecyc() {

        sr_refresh
        adapter = CommonRecyclerAdapter(
            R.layout.item_rv_losts,
            lostData,
            {
                this.tv_lost_item_stu_name.text = "姓名：${it.name}"
                this.tv_lost_item_stu_num.text = "学号：${it.stuNumber}"
                this.tv_lost_item_stu_college.text = "学院：${it.college}"
                Glide.with(this@LostFragment)
                    .load(it.cardPic)
                    .into(this.iv_lost_item_ic)
            }
        )
        rv_losts_message.adapter = adapter
        viewModel.getLostCards(nextIndex).observe(this@LostFragment, mObserver)
    }

    /**
     * 下拉刷新数据
     *
     */
    override fun onRefresh() {
        lostData.clear()
        nextIndex = 0
        viewModel.getLostCards(nextIndex)
    }

    private fun loadMoreData() {
        viewModel.getLostCards(nextIndex)
    }
}