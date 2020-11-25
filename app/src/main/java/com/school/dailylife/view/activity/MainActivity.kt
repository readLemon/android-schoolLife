package com.school.dailylife.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.school.dailylife.R
import com.school.dailylife.view.fragment.BaseFragment
import com.school.dailylife.view.fragment.MainFragment
import com.school.dailylife.view.fragment.SortFragment
import com.school.dailylife.view.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
    , BottomNavigationView.OnNavigationItemSelectedListener {

    private var preNavPos = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_main.setOnNavigationItemSelectedListener(this)
        vp_main.adapter =
            MyViewPagerAdapter(
                supportFragmentManager,
                this.lifecycle
            )
        vp_main.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                    nav_main.menu.getItem(position).setChecked(true)
            }
        })
    }


    inner class MyViewPagerAdapter(fm: FragmentManager, life: Lifecycle): FragmentStateAdapter(fm,life) {

        val mFragmentClass = arrayOf(
            MainFragment::class.java,
            SortFragment::class.java,
            MineFragment::class.java
        )
        val mFragmentInstance = HashMap<Class<out BaseFragment>, Fragment>();

        override fun getItemCount() = mFragmentClass.size

        override fun createFragment(position: Int): Fragment {
            val fmClass = mFragmentClass[position]
            val tarFragment = mFragmentInstance[fmClass]

            return if(tarFragment == null) {
                val mFragment = fmClass.newInstance()
                mFragmentInstance[fmClass] = mFragment
                mFragment
            }else {
                tarFragment
            }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var pos = 0

        when(item.itemId) {
            R.id.nav_main_graph -> pos = 0

            R.id.nav_sort -> pos = 1

            R.id.nav_mine -> pos = 2
        }

        if (pos != preNavPos) {
            vp_main.setCurrentItem(pos, false)
            preNavPos = pos
        }


        return true
    }
}