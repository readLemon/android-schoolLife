package com.school.dailylife.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.school.dailylife.R
import com.school.dailylife.config.REQUEST_CAMERA_CODE
import com.school.dailylife.config.REQUEST_PHOTO_LIST_CODE
import com.school.dailylife.config.checkStoragePermission
import com.school.dailylife.config.getConfig
import com.school.dailylife.util.toast
import com.school.dailylife.viewmodel.LostViewModel
import com.yuyh.library.imgsel.ISNav
import kotlinx.android.synthetic.main.activity_pulish_stucard.*

class PublishStuCardActivity : BaseActivity() {
    override val contentViewId: Int
        get() = R.layout.activity_pulish_stucard

    private val viewModel by viewModels<LostViewModel>()


    override fun initView(savedInstanceState: Bundle?) {
        btn_select_photo.setOnClickListener {
            checkStoragePermission(this@PublishStuCardActivity)
            // 跳转到图片选择器
            ISNav.getInstance().toListActivity(this, getConfig(1), REQUEST_PHOTO_LIST_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 图片选择/拍照结果回调

        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_PHOTO_LIST_CODE -> {
                    val pathList = data.getStringArrayListExtra("result") as List<String>
                    fillCardData(pathList[0])
                }

                REQUEST_CAMERA_CODE -> {
                }
            }
        }
    }

    /**
     * 把图片解析出来的数据进行填充
     *
     * @param imagePath
     */
    private fun fillCardData(imagePath: String) {
        viewModel.uploadStuCard(imagePath).observe(this@PublishStuCardActivity, Observer {card->
            if (card.status == 0) {
                toast("你的照片无法识别，请从新拍摄")
                return@Observer
            }
            tv_card_name.text = "姓名：${card.name}"
            tv_card_stu_num.text = "学号：${card.stuNumber}"
            tv_card_college.text = "学院：${card.college}"
            if (card.cardPic.isNotEmpty()) {
                btn_pub.visibility = View.VISIBLE
                btn_pub.setOnClickListener {
                    viewModel.publishCard(
                        stuNumber = card.stuNumber,
                        name = card.name,
                        college = card.college,
                        stuCardPic = card.cardPic
                    ).observe(this@PublishStuCardActivity, Observer {
                        if (it) {
                            toast("上传成功")
                            finish()
                        } else {
                            toast("上传失败")
                        }
                    })
                }
            }
        })
    }

}