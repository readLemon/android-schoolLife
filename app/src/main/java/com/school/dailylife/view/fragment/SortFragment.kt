package com.school.dailylife.view.fragment

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.school.dailylife.R
import com.school.dailylife.bean.SortTypeBean
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import com.school.dailylife.view.adapter.SortFmAdapter
import com.school.dailylife.viewmodel.SortFmViewModel
import kotlinx.android.synthetic.main.fragment_sort.*
import kotlinx.android.synthetic.main.item_rv_sort_slide.view.*
import kotlin.let as let1

/**
 * Created by chenyang
 * on 20-11-5
 */
class SortFragment : LazyFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_sort

    private val viewmodel by viewModels<SortFmViewModel>({requireActivity()})

    @RequiresApi(Build.VERSION_CODES.M)
    override fun afterViewCteated(view: View) {
        observedata()
    }

    override fun initData() {
        viewmodel.getSort()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun observedata() {
        val data = mutableListOf<SortTypeBean>()

        val adapter = SortFmAdapter(
            R.layout.item_rv_sort_slide
            , data
            , {
              viewmodel.pos.value = it
            }
            ,{ sortTypeBean ->
                this.tv_sort_type_name.text = sortTypeBean.type
            })

        rv_sort_slide.adapter = adapter
        vp_sort_detail.adapter = MyViewPagerAdapter(childFragmentManager, this@SortFragment.lifecycle)
        viewmodel.sortBean.observe{
            if (it != null) {
                data.clear()
                data.addAll(it.sortTypeBeanList)
                adapter.notifyDataSetChanged()
            }
        }

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