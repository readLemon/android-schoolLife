package com.school.dailylife.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 * Created by chenyang
 * on 20-11-8
 */
class CommonRecyclerAdapter<T>(
    private val layoutId: Int,
    private val datas: List<T>,
    private val binHolder: View.(T) -> Unit = {}
) : RecyclerView.Adapter<CommonRecyclerAdapter.SimpleViewHolder>() {


    private var itemClick: T.() -> Unit = {}
    constructor(
        layoutId: Int,
        datas: List<T>,
        bindHolder: View.(T) -> Unit = {},
        itemClick: T.() -> Unit = {}
    ) : this(layoutId, datas, bindHolder) {
        this.itemClick = itemClick

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return SimpleViewHolder(v)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.itemView.binHolder(datas.get(holder.adapterPosition))
        holder.itemView.setOnClickListener {datas.get(position).itemClick() }

    }

    override fun getItemCount() = datas.size


    class SimpleViewHolder(v: View) : ViewHolder(v)
}