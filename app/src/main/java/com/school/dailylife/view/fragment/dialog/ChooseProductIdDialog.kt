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
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import kotlinx.android.synthetic.main.dialog_choose_product_id.*
import kotlinx.android.synthetic.main.item_rv_choose_product_id.view.*

/**
 * Created by chenyang
 * on 20-12-15
 */
class ChooseProductIdDialog(val list: List<String>, val onChoosed: (String) -> Unit) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_choose_product_id, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_choose_product_id.adapter = CommonRecyclerAdapter(
            R.layout.item_rv_choose_product_id,
            list,
            {
                tv_choose_product_id_str.text = it
            }, {
                onChoosed(this)
                dismiss()
            }
        )
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