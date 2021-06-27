package com.school.dailylife.view.fragment.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.school.dailylife.App
import com.school.dailylife.R
import com.school.dailylife.bean.SoledProductBean
import com.school.dailylife.util.ScreenSizeType
import com.school.dailylife.util.getScreenHeight
import com.school.dailylife.util.getScreenWidth
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import com.school.dailylife.view.widget.SlideLayout
import com.school.dailylife.view.widget.onSlideChangeListener
import com.school.dailylife.viewmodel.MineFmViewModel
import kotlinx.android.synthetic.main.dialog_show_my_pubed_product.*
import kotlinx.android.synthetic.main.item_mine_pubed_products.view.*

/**
 * Created by chenyang
 * on 20-12-15
 */
class ShowMyPubedProductDialog(private val dataBean: List<SoledProductBean.Product>) : DialogFragment() {

    private var mSlideLayout: SlideLayout ?= null
    private val mSlideListener: onSlideChangeListener = object :onSlideChangeListener{
        override fun onMenuOpen(slide: SlideLayout) {
            mSlideLayout = slide
        }

        override fun onMenuClose(slide: SlideLayout) {
            if (mSlideLayout != null) {
                mSlideLayout = null
            }
        }

        override fun onMenuClick(slide: SlideLayout) {
            mSlideLayout?.closeMenu()
        }

    }

    private val viewModel by viewModels<MineFmViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_show_my_pubed_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = CommonRecyclerAdapter(
            R.layout.item_mine_pubed_products,
            dataBean,
            {
                tv_dialog_show_pubed_title.text = it.title
                tv_dialog_mine_pubed_desc.text = it.description
                tv_dialog_pubed_price.text = "$${it.price}"
                tv_dialog_product_isSoled.text = if (it.status == PRODUCT_SELLING_STATE) "已卖出" else "在售"
                if (this.rootView is SlideLayout) {
                    (this.rootView as SlideLayout) .slideListener = mSlideListener
                }
                tv_item_soled.setOnClickListener { view->
                    viewModel.changeProductState(it.pid, PRODUCT_SOLED_STATE)
                }
            }
        )
        rv_mine_soled_product.adapter = adapter

        iv_close_pubed_dialog.setOnClickListener {
            dismiss()
        }
    }


    @SuppressLint("PrivateResource")
    override fun onStart() {
        super.onStart()
        isCancelable = true
        dialog?.setCanceledOnTouchOutside(true)
        requireActivity().windowManager.defaultDisplay.getMetrics(DisplayMetrics())
        dialog?.window?.setLayout(
            App.context.getScreenWidth(), App.context.getScreenHeight(
                ScreenSizeType.BOTH
            )
        )
        dialog?.window?.setBackgroundDrawableResource(R.color.mtrl_btn_transparent_bg_color)
    }

    companion object {
        private val PRODUCT_SELLING_STATE = 1
        private val PRODUCT_SOLED_STATE = 2
    }

}