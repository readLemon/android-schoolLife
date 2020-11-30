package com.school.dailylife.view.fragment

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.school.dailylife.R
import com.school.dailylife.bean.SortDetailBean
import com.school.dailylife.bean.SortTypeBean
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_sort.*
import kotlinx.android.synthetic.main.item_rv_sort_slide.view.*

/**
 * Created by chenyang
 * on 20-11-5
 */
class SortFragment : BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_sort


    override fun initView(view: View) {
        fillFakdata()
    }

    private fun fillFakdata() {
        val data = listOf(
            SortTypeBean(listOf(),0,"热门食品1"),
            SortTypeBean(listOf(),0,"热门食品2"),
            SortTypeBean(listOf(),0,"热门食品3"),
            SortTypeBean(listOf(),0,"热门食品4")
        )
        val adapter = CommonRecyclerAdapter(
            R.layout.item_rv_sort_slide
            , data
            , { sortTypeBean ->
                this.tv_sort_type_name.text = sortTypeBean.type
            }
            , {
                Toast.makeText(context, "i was clicked !", Toast.LENGTH_SHORT).show()
            })

        rv_sort_slide.adapter = adapter

        vp_sort_detail.adapter = MyViewPagerAdapter(childFragmentManager, this@SortFragment.lifecycle)
    }

    inner class MyViewPagerAdapter(fm: FragmentManager, life: Lifecycle): FragmentStateAdapter(fm,life) {

        val mFragmentClass = arrayOf(
            SortDetailFragment::class.java
        )
        val mFragmentInstance = HashMap<Class<out BaseFragment>, Fragment>();

        override fun getItemCount() = mFragmentClass.size

        override fun createFragment(position: Int): Fragment {
//            val fmClass = mFragmentClass[position]
//            val tarFragment = mFragmentInstance[fmClass]
//
//            return if(tarFragment == null) {
//                val mFragment = fmClass.newInstance()
//                mFragmentInstance[fmClass] = mFragment
//                mFragment
//            }else {
//                tarFragment
//            }
            return if(mFragmentInstance[mFragmentClass[0]] == null) {
                val tmp = mFragmentClass[0].newInstance()
                mFragmentInstance[mFragmentClass[0]] = tmp
                tmp
            } else {
                mFragmentInstance[mFragmentClass[0]]!!
            }
        }

    }
}