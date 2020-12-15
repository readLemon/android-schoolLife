package com.school.dailylife.view.adapter

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.mredrock.runtogether.App
import com.school.dailylife.R

/**
 * Created by chenyang
 * on 20-12-2
 */
class SortFmAdapter<T>(
    val layoutId: Int,
    val datas: List<T>,
    val tellChildFragment: (Int) -> Unit,
    val bindHolder: View.(T) -> Unit = {}
) : CommonRecyclerAdapter<T>(layoutId, datas, bindHolder) {

    private var isFirst = true
    private var preItemClickView: View? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position == 0 && isFirst) {
            isFirst = false
            holder.itemView.setBackgroundColor(App.context.getColor(R.color.color_white))
            preItemClickView = holder.itemView
            tellChildFragment(position)
        }

        holder.itemView.setOnClickListener {
            if (preItemClickView != it) {
                if (preItemClickView != null) {
                    preItemClickView?.setBackgroundColor(it.context.getColor(R.color.colorPrimary))
                }
                it.setBackgroundColor(it.context.getColor(R.color.color_white))
                preItemClickView = it
                tellChildFragment(position)
            }

        }
    }
}