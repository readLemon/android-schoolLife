package com.school.dailylife.config

import android.Manifest
import android.graphics.Color
import androidx.fragment.app.FragmentActivity
import com.permissionx.guolindev.PermissionX
import com.school.dailylife.R
import com.school.dailylife.util.toast
import com.yuyh.library.imgsel.config.ISListConfig

/**
 * Created by chenyang
 * on 20-12-17
 */

//商品的两个属性
val typeId = mapOf("针织衫" to 1, "牛仔裤" to 2, "连衣裙" to 3, "羽绒服" to 4, "相机" to 4, "耳机" to 5)
val categoryId = mapOf("男装" to 1, "女装" to 2, "数码" to 3)

fun checkStoragePermission(activity: FragmentActivity) {
    PermissionX.init(activity)
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
                activity.toast("All permissions are granted")
            } else {
                activity.toast("These permissions are denied: $deniedList")
            }
        }
}

// 自由配置选项
fun getConfig(maxNum: Int) =

    ISListConfig.Builder() // 是否多选, 默认true
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
        .maxNum(maxNum) // 最大选择图片数量，默认9
        .build()

