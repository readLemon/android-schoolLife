package com.school.dailylife.view.activity

import android.Manifest
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.permissionx.guolindev.PermissionX
import com.school.dailylife.R
import com.school.dailylife.config.IS_FIRST_LOGIN
import com.school.dailylife.util.defaultSharedPrefrence
import com.school.dailylife.util.toast
import com.school.dailylife.view.fragment.*
import com.school.dailylife.view.fragment.dialog.GetMsgDialog
import com.school.dailylife.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var preNavPos = -1
    override val contentViewId: Int
        get() = R.layout.activity_main

    private val userViewModel by viewModels<UserViewModel>()

    override fun beforeSetContentViewId(savedInstanceState: Bundle?) {
        PermissionX.init(this)
            .permissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .onExplainRequestReason { scope, deniedList ->
                val message = "需要您同意以下权限才能正常使用"
                scope.showRequestReasonDialog(deniedList, message, "确定", "取消")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    this.toast("All permissions are granted")
                } else {
                    this.toast("These permissions are denied: $deniedList")
                }
            }

    }

    override fun initView(savedInstanceState: Bundle?) {

        nav_main.setOnNavigationItemSelectedListener(this)
        vp_main.adapter =
            MyViewPagerAdapter(
                supportFragmentManager,
                this.lifecycle
            )
        vp_main.offscreenPageLimit = 3 //预先创建出所有的Fragment，防止切换造成的频繁销毁和创建
        vp_main.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                nav_main.menu.getItem(position).setChecked(true)
            }
        })

        if (defaultSharedPrefrence.getBoolean(IS_FIRST_LOGIN, true)) {
//        if (true) {
            firstLogin()
            defaultSharedPrefrence.edit {
                putBoolean(IS_FIRST_LOGIN, false)
            }
        }
    }

    private fun firstLogin() {
        val dialog = GetMsgDialog(userViewModel)
        dialog.show(supportFragmentManager, "firstLogin")
    }





    inner class MyViewPagerAdapter(fm: FragmentManager, life: Lifecycle) :
        FragmentStateAdapter(fm, life) {

        val mFragmentClass = arrayOf(
            MainFragment::class.java,
            SortFragment::class.java,
            LostFragment::class.java,
            MineFragment::class.java
        )
        val mFragmentInstance = HashMap<Class<out BaseFragment>, Fragment>();

        override fun getItemCount() = mFragmentClass.size

        override fun createFragment(position: Int): Fragment {
            val fmClass = mFragmentClass[position]
            val tarFragment = mFragmentInstance[fmClass]

            return if (tarFragment == null) {
                val mFragment = fmClass.newInstance()
                mFragmentInstance[fmClass] = mFragment
                mFragment
            } else {
                tarFragment
            }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var pos = 0

        when (item.itemId) {
            R.id.nav_main_graph -> pos = 0

            R.id.nav_sort -> pos = 1

            R.id.nav_lost -> pos = 2

            R.id.nav_mine -> pos = 3
        }

        if (pos != preNavPos) {
            vp_main.setCurrentItem(pos, false)
            preNavPos = pos
        }


        return true
    }
}