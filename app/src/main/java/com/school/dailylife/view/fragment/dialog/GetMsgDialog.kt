package com.school.dailylife.view.fragment.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mredrock.runtogether.App
import com.school.dailylife.R
import com.school.dailylife.util.ScreenSizeType
import com.school.dailylife.util.getScreenHeight
import com.school.dailylife.util.getScreenWidth
import kotlinx.android.synthetic.main.dialog_get_msg.*

/**
 * Created by chenyang
 * on 20-12-15
 */
class GetMsgDialog() : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_get_msg, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_dialog_get_msg_sure.setOnClickListener {
            dismiss()
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