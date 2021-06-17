package com.school.dailylife.view.fragment.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.school.dailylife.App
import com.school.dailylife.R
import com.school.dailylife.config.NET_REQUEST_SUCCESSFULL
import com.school.dailylife.util.ScreenSizeType
import com.school.dailylife.util.getScreenHeight
import com.school.dailylife.util.getScreenWidth
import com.school.dailylife.viewmodel.SettingViewModel
import kotlinx.android.synthetic.main.dialog_edit_my_contact.*

/**
 * Created by chenyang
 * on 20-12-15
 */
class EditMyContactDialog(val viewmodel: SettingViewModel) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_edit_my_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_dialog_contact_sure.setOnClickListener {
            val phone = et_dialog_get_msg_sex.text.toString()
            val qq = et_dialog_get_msg_age.text.toString()
            viewmodel.uploadContactWayPhoto(phone, qq)
                .subscribe({
                    if (it.status == NET_REQUEST_SUCCESSFULL) {
                        Toast.makeText(requireContext(), "上传资料成功", Toast.LENGTH_SHORT).show()
                        dismiss()
                    }
                }, {
                    it.printStackTrace()
                    Toast.makeText(requireContext(), "上传资料失败", Toast.LENGTH_SHORT).show()
                    dismiss()
                })
        }

    }


    @SuppressLint("PrivateResource")
    override fun onStart() {
        super.onStart()
        isCancelable = true
        requireActivity().windowManager.defaultDisplay.getMetrics(DisplayMetrics())
        dialog?.window?.setLayout(
            App.context.getScreenWidth(), App.context.getScreenHeight(
                ScreenSizeType.BOTH
            )
        )
        dialog?.window?.setBackgroundDrawableResource(R.color.mtrl_btn_transparent_bg_color)
    }

}