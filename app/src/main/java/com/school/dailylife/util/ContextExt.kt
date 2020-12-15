package com.school.dailylife.util

import android.content.Context
import android.graphics.Point
import android.view.WindowManager

/**
 * Created by chenyang
 * on 20-12-15
 */

/**
 * 获取屏幕的真实尺寸
 */
enum class ScreenSizeType {
    STATUS_BAR,  // 获取的尺寸只包含app和状态栏的高度
    NAVIGATION_BAR,  // 获取的尺寸只包含app和导航栏的高度
    BOTH,  // 获取的尺寸就是手机的物理尺寸
    NONE  // 获取的高度不包含状态栏和导航栏的高度
}

fun Context.getScreenHeight(type: ScreenSizeType = ScreenSizeType.BOTH) = getScreenSize(type).y

fun Context.getScreenWidth() = getScreenSize().x

fun Context.getScreenSize(type: ScreenSizeType = ScreenSizeType.BOTH): Point {
    val size = Point()
    (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getRealSize(size)
    val navigationBarHeight = resources.getDimension(
        resources.getIdentifier(
            "navigation_bar_height",
            "dimen",
            "android"
        )).toInt()
    val statusBarHeight = resources.getDimension(
        resources.getIdentifier(
            "status_bar_height",
            "dimen",
            "android"
        )).toInt()
    when (type) {
        ScreenSizeType.NAVIGATION_BAR -> size.y -= statusBarHeight
        ScreenSizeType.STATUS_BAR -> size.y -= navigationBarHeight
        ScreenSizeType.NONE -> size.y -= (statusBarHeight + navigationBarHeight)
        ScreenSizeType.BOTH -> Unit
    }
    return size
}