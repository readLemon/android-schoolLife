package com.school.dailylife.view.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.school.dailylife.R
import com.school.dailylife.config.REQUEST_PHOTO_LIST_CODE
import com.school.dailylife.viewmodel.SettingViewModel
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity() {
    override val contentViewId: Int
        get() = R.layout.activity_setting

    private val viewmodel by viewModels<SettingViewModel>()


    override fun initView(savedInstanceState: Bundle?) {

        cl_setting_upload_contact_way.setOnClickListener {
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
                .maxNum(1) // 最大选择图片数量，默认9
                .build()

            // 跳转到图片选择器
            ISNav.getInstance().toListActivity(this, config, REQUEST_PHOTO_LIST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {

            when (requestCode) {
                REQUEST_PHOTO_LIST_CODE -> {
                    val pathList = data.getStringArrayListExtra("result") as List<String>

                    for (path in pathList) {
                        Log.d("设置选择的图片地址", path)
                    }
                }
            }
        }
    }

}