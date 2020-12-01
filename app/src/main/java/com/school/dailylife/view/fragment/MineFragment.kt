package com.school.dailylife.view.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.school.dailylife.R
import com.school.dailylife.util.CurrentUser
import com.school.dailylife.util.loadPic
import com.school.dailylife.view.activity.LoginActivity
import com.school.dailylife.viewmodel.MineFmViewModel
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Created by chenyang
 * on 20-11-5
 */
class MineFragment : LazyFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_mine

    private val viewmodel by viewModels<MineFmViewModel>({ requireActivity() })

    override fun initData() {
        viewmodel.getMineData()
    }

    override fun afterViewCteated(view: View) {
        if (CurrentUser.getCurrentUser() == null) {
            tv_fm_mine_username.setOnClickListener {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            }
        } else {
//            Glide.with(requireContext())
//                .load(CurrentUser.getCurrentUser().avatarPic)
//                .into(iv_mine_user_avatar)
            iv_mine_user_avatar.loadPic(CurrentUser.getCurrentUser().avatarPic)
            tv_fm_mine_username.setText(CurrentUser.getCurrentUser().nickname)
            tv_fm_mine_soled_num.invalidate()
        }

        viewmodel.mineBaseData.observe {
            if (it != null) {
                tv_fm_mine_reputation_score.setText("${it.reputationCore}\n信誉评分")
                tv_fm_mine_comment_num.setText("${it.accumulatedComment}\n累计评价")
                tv_fm_mine_solding_num.setText("${it.soldingNum}\n在售商品数")
                tv_fm_mine_soled_num.setText("${it.soledNum}\n已售商品")
            }
        }

    }


}