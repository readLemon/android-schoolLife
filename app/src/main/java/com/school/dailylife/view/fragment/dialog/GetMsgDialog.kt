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
import com.school.dailylife.util.ScreenSizeType
import com.school.dailylife.util.getScreenHeight
import com.school.dailylife.util.getScreenWidth
import com.school.dailylife.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.dialog_get_msg.*
import java.lang.NumberFormatException

/**
 * 用户第一次登录的时候用于获取用于的一些基本信息来做到默认的推荐
 * Created by chenyang
 * on 20-12-15
 */
class GetMsgDialog(val viewModel: UserViewModel) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_get_msg, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_dialog_get_msg_sure.setOnClickListener {
            try {

                val sex = et_dialog_get_msg_sex.text.toString().toInt()
                val age = et_dialog_get_msg_age.text.toString().toInt()
                val enterSchoolYear = et_dialog_get_msg_enter_school_year.text.toString().toInt()
                val hobby = et_dialog_get_msg_hobby.text.toString()
                val major = et_dialog_get_msg_major.text.toString()

                viewModel.postUserInformation(sex, age, enterSchoolYear, hobby, major)
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            } finally {

                dismiss()

            }

        }

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