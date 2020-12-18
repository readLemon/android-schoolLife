package com.school.dailylife.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.school.dailylife.R
import com.school.dailylife.config.*
import com.school.dailylife.util.loadPic
import com.school.dailylife.util.toast
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import com.school.dailylife.view.fragment.dialog.WaitDialog
import com.school.dailylife.viewmodel.PublishViewModel
import com.yuyh.library.imgsel.ISNav
import kotlinx.android.synthetic.main.activity_publish.*
import kotlinx.android.synthetic.main.item_pubing_selected_pic.view.*

class PublishActivity : BaseActivity(), View.OnClickListener {
    override val contentViewId: Int
        get() = R.layout.activity_publish

    private var adapter: RecyclerView.Adapter<CommonRecyclerAdapter.SimpleViewHolder>? = null
    private val pathList by lazy { mutableListOf<String>() }
    private val viewmodel by viewModels<PublishViewModel>()
    private var dialog: DialogFragment? = null


    override fun initView(savedInstanceState: Bundle?) {

        iv_pub_back.setOnClickListener(this)
        //发布按钮
        tv_pub_sure_pub.setOnClickListener(this)
        iv_pub_add_image.setOnClickListener(this)

        adapter = CommonRecyclerAdapter(
            R.layout.item_pubing_selected_pic,
            this.pathList,
            { this.iv_pubing_selected_pic.loadPic(it) }
        )
        rv_pubing_show_selected_pic.adapter = adapter

        viewmodel.isUploadProductSuccess.observe(this@PublishActivity, {
            if (it) {
                toast("上传商品成功！")
            } else {
                if (dialog != null && dialog?.isVisible ?: false) {
                    dialog?.dismiss()
                }
                toast("上传商品失败！")
            }
        })
    }


    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.iv_pub_back -> finish()

            R.id.tv_pub_sure_pub -> {
                val title = et_pubing_product_title.text.toString()
                val desc = et_pub_pro_desc.text.toString()

                if (title.trim().isEmpty()) {
                    toast("商品名字不能为空")
                    return
                }

                if (desc.trim().isEmpty()) {
                    toast("商品描述不能为空")
                    return
                }
                viewmodel.uploadDescPhotos(
                    typeId["男装"] ?: 1,
                    categoryId["牛仔裤"] ?: 2,
                    title,
                    desc,
                    pathList
                )
                dialog = WaitDialog()
                dialog?.show(supportFragmentManager, "tag")
            }

            R.id.iv_pub_add_image -> {
                checkStoragePermission(this@PublishActivity)
                // 跳转到图片选择器
                ISNav.getInstance().toListActivity(this, config, REQUEST_PHOTO_LIST_CODE)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 图片选择/拍照结果回调

        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_PHOTO_LIST_CODE -> {
                    val pathList = data.getStringArrayListExtra("result") as List<String>
                    if (pathList.isEmpty()) return
                    this.pathList.clear()
                    this.pathList.addAll(pathList)

                    iv_pub_add_image.visibility = View.GONE
                    rv_pubing_show_selected_pic.visibility = View.VISIBLE
                    adapter?.notifyDataSetChanged()
                }

                REQUEST_CAMERA_CODE -> {

                }

            }
        }
    }
}