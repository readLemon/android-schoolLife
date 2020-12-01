package com.school.dailylife.view.fragment

import android.content.Intent
import android.view.View
import com.school.dailylife.R
import com.school.dailylife.view.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Created by chenyang
 * on 20-11-5
 */
class MineFragment : LazyFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_mine

    override fun afterViewCteated(view: View) {
        tv_fm_mine_goto_login.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun initData() {

    }
}