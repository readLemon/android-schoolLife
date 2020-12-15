package com.school.dailylife.view.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.school.dailylife.R
import com.school.dailylife.config.REQUEST_CAMERA_CODE
import com.school.dailylife.config.REQUEST_PHOTO_LIST_CODE
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import kotlinx.android.synthetic.main.activity_publish.*


class PublishActivity : BaseActivity(), View.OnClickListener {
    override val contentViewId: Int
        get() = R.layout.activity_publish

    override fun initView(savedInstanceState: Bundle?) {

        iv_pub_back.setOnClickListener(this)
        //发布按钮
        tv_pub_sure_pub.setOnClickListener(this)
        iv_pub_add_image.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.iv_pub_back -> finish()

            R.id.tv_pub_sure_pub -> {
            }

            R.id.iv_pub_add_image -> {
                // 自由配置选项
                val config = ISListConfig.Builder() // 是否多选, 默认true
                    .multiSelect(true) // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                    .rememberSelected(false) // “确定”按钮背景色
                    .btnBgColor(Color.GRAY) // “确定”按钮文字颜色
                    .btnTextColor(Color.BLUE) // 使用沉浸式状态栏
                    .statusBarColor(Color.parseColor("#3F51B5"))
                    .backResId(R.drawable.ic_back) // 返回图标ResId
                    .title("图片") // 标题文字颜色
                    .titleColor(Color.WHITE) // TitleBar背景色
                    .titleBgColor(Color.parseColor("#3F51B5"))
                    .cropSize(1, 1, 200, 200)// 裁剪大小。needCrop为true的时候配置
                    .needCrop(true)
                    .needCamera(false)// 第一个是否显示相机，默认true
                    .maxNum(4) // 最大选择图片数量，默认9
                    .build()

                // 跳转到图片选择器
                ISNav.getInstance().toListActivity(this, config, REQUEST_PHOTO_LIST_CODE)

            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 图片选择/拍照结果回调

        if (resultCode == RESULT_OK && data != null) {

            when (requestCode) {
                REQUEST_PHOTO_LIST_CODE -> {
                    val pathList = data.getStringArrayListExtra("result") as List<String>

                    for (path in pathList) {
                        Log.d("选择的图片地址", path)
                    }
                }


                REQUEST_CAMERA_CODE -> {

                }

            }
        }
    }
}