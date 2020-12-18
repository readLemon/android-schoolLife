package com.school.dailylife.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.school.dailylife.R
import com.school.dailylife.bean.KeyValueBean
import com.school.dailylife.view.adapter.CommonRecyclerAdapter.SimpleViewHolder
import kotlinx.android.synthetic.main.item_rv_key_value.view.*

/**
 * Created by chenyang
 * on 20-12-18
 */
class KVAdapter(val list: List<KeyValueBean>) :
    RecyclerView.Adapter<SimpleViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimpleViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_key_value, parent, false)
        return SimpleViewHolder(v)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.itemView.tv_rv_key.setText(list.get(position).k)
        holder.itemView.tv_rv_value.setText(list.get(position).v)
    }

    override fun getItemCount() = list.size
}