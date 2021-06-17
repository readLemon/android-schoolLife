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
import com.school.dailylife.bean.MainProductBean
import com.school.dailylife.util.ScreenSizeType
import com.school.dailylife.util.getScreenHeight
import com.school.dailylife.util.getScreenWidth
import com.school.dailylife.view.adapter.SearchAdapter
import com.school.dailylife.viewmodel.MainFmViewModel
import kotlinx.android.synthetic.main.dialog_search_result.*

/**
 * Created by chenyang
 * on 20-12-15
 */
class SearchResultDialog(val viewmodel: MainFmViewModel) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_search_result, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val datas = mutableListOf<MainProductBean>()

        val adapter = SearchAdapter(datas, requireContext())
        rv_search_result_product.adapter = adapter

        viewmodel.searchData.observe(requireActivity(), {
            if (!it.isNullOrEmpty()) {
                datas.clear()
                datas.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

        iv_close_search_dialog.setOnClickListener { dismiss() }
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