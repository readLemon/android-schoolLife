package com.school.dailylife.view.fragment

import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.viewModels
import com.school.dailylife.R
import com.school.dailylife.bean.LostPickerBean
import com.school.dailylife.bean.TextItem
import com.school.dailylife.util.CurrentUser
import com.school.dailylife.util.loadPic
import com.school.dailylife.view.activity.LoginActivity
import com.school.dailylife.view.activity.SettingActivity
import com.school.dailylife.view.fragment.dialog.ShowMyPubedProductDialog
import com.school.dailylife.view.fragment.dialog.ShowPickerInfoDialog
import com.school.dailylife.viewmodel.MineFmViewModel
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Created by chenyang
 * on 20-11-5
 */
class MineFragment : LazyFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_mine

    private val viewmodel by viewModels<MineFmViewModel>()
    private var mLostPickerBean: LostPickerBean? = null

    override fun initData() {
        viewmodel.getMineData()
    }

    override fun onResume() {
        super.onResume()

    }
    override fun afterViewCteated(view: View) {
        if (CurrentUser.getCurrentUser() == null) {
            tv_fm_mine_username.setOnClickListener {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            }
        } else {
            iv_mine_user_avatar.loadPic(CurrentUser.getCurrentUser().avatarPic)
            tv_fm_mine_username.text = CurrentUser.getCurrentUser().nickname
            tv_fm_mine_soled_num.invalidate()
        }

        viewmodel.mineBaseData.observe {
            if (it != null) {
                tv_fm_mine_reputation_score.text = "${it.reputationCore}\n信誉评分"
                tv_fm_mine_comment_num.text = "${it.accumulatedComment}\n累计评价"
                tv_fm_mine_solding_num.text = "${it.soldingNum}\n在售商品数"
                tv_fm_mine_soled_num.text = "${it.soledNum}\n已售商品"
                mLostPickerBean = it.lostPickerBean
                if (!TextUtils.isEmpty(mLostPickerBean?.stuNumber)) {
                    iv_red_point.visibility = View.VISIBLE
                    iv_private_msg.setOnClickListener {
                        mLostPickerBean?.let { lostPickerBean ->
                            ShowPickerInfoDialog(lostPickerBean).show(
                                childFragmentManager,
                                "ShowPickerInfoDialog"
                            )
                        }
                    }
                }
                val wallData = mutableListOf<String>()
                for (tmp in it.commentWall) {
                    wallData.add(tmp.commentContent)
                }

                val textItems = mutableListOf<TextItem>()
                for (i in 0 until wallData.size) {
                    val item = TextItem()
                    item.index = 10F
                    item.value = wallData[i % 10]
                    textItems.add(item)
                }

                tw_comment.setData(textItems, requireContext())

            }
        }

        viewmodel.soledBeans.observe { it ->
            if (!it.isNullOrEmpty()) {
                ShowMyPubedProductDialog(it.filter { it.status == 1 }).show(
                    childFragmentManager,
                    "ShowMyPubedProductDialog"
                )
            }
        }

        iv_mine_setting.setOnClickListener {
            startActivity(Intent(requireActivity(), SettingActivity::class.java))
        }

        iv_mine_pubed_product.setOnClickListener {
            viewmodel.getMyPubedProducts()
        }


    }


}