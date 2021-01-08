package com.school.dailylife.view.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.school.dailylife.R
import com.school.dailylife.bean.MainProductBean
import com.school.dailylife.config.INTENT_KEY_PRODUCT_ID
import com.school.dailylife.config.INTENT_KEY_PRODUCT_OWNER_ID
import com.school.dailylife.util.loadPic
import com.school.dailylife.view.activity.ProductDetailActivity
import kotlinx.android.synthetic.main.item_rv_search.*

/**
 * Created by chenyang
 * on 20-12-2
 */
class SearchAdapter(
    val datas: List<MainProductBean>,
    val context: Context
) : RecyclerView.Adapter<CommonRecyclerAdapter.SimpleViewHolder>() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CommonRecyclerAdapter.SimpleViewHolder, position: Int) {
        val bean = datas.get(position)
        holder.itemView.apply {


            this.findViewById<AppCompatTextView>(R.id.tv_search_rv_title).text = bean.title
            this.findViewById<AppCompatTextView>(R.id.tv_search_rv_price).text = "$${bean.price}"
            this.findViewById<AppCompatImageView>(R.id.iv_search_rv_product_pic)
                .loadPic(bean.productPic)
            this.findViewById<AppCompatTextView>(R.id.tv_search_rv_username).text = bean.username

            Glide.with(context)
                .load(bean.userAvatar)
                .into(this.findViewById<AppCompatImageView>(R.id.iv_search_rv_product_pic))
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java)

            intent.putExtra(INTENT_KEY_PRODUCT_ID, bean.pid)
            intent.putExtra(INTENT_KEY_PRODUCT_OWNER_ID, bean.uid)
            context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonRecyclerAdapter.SimpleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_search, parent, false)
        return CommonRecyclerAdapter.SimpleViewHolder(view)
    }

    override fun getItemCount() = datas.size
}