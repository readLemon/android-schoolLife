package com.school.dailylife.view.fragment.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.school.dailylife.App
import com.school.dailylife.R
import com.school.dailylife.bean.LostPickerBean
import com.school.dailylife.util.ScreenSizeType
import com.school.dailylife.util.getScreenHeight
import com.school.dailylife.util.getScreenWidth
import kotlinx.android.synthetic.main.dialog_show_picker_info.*

/**
 * Created by chenyang
 * on 20-12-15
 */
class ShowPickerInfoDialog(private val lostPickerBean: LostPickerBean) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_show_picker_info, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        iv_close_picker_info_dialog.setOnClickListener { dismiss() }
        tv_card_stu_number.text = "卡片-学号：${lostPickerBean.stuNumber}"
        tv_card_stu_name.text = "卡片-姓名：${lostPickerBean.name}"
        tv_card_stu_college.text = "卡片-姓名：${lostPickerBean.college}"
        tv_picker_stu_qq.text = "联系方式-qq：${lostPickerBean.pickQQ}"
        tv_picker_stu_tel.text = "联系方式-qq：${lostPickerBean.pickTel}"
    }


    @SuppressLint("PrivateResource")
    override fun onStart() {
        super.onStart()
        isCancelable = false
        requireActivity().windowManager.defaultDisplay.getMetrics(DisplayMetrics())
        dialog?.window?.setLayout(
            App.context.getScreenWidth(), App.context.getScreenHeight(
                ScreenSizeType.BOTH
            )
        )
        dialog?.window?.setBackgroundDrawableResource(R.color.mtrl_btn_transparent_bg_color)
    }

}