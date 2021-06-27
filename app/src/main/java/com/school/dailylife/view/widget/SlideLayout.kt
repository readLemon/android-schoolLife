package com.school.dailylife.view.widget

/**
 * Created by chenyang
 * on 2021/6/27
 */

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Scroller
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.abs
import kotlin.properties.Delegates

/**
 * Created by chenyang
 * on 20-3-13
 */
class SlideLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var contentView by Delegates.notNull<View>()
    private var menuView by Delegates.notNull<View>()

    private var menuWidth by Delegates.notNull<Int>()
    private var menuHeight by Delegates.notNull<Int>()
    private var contentWidth by Delegates.notNull<Int>()
    private var contentHeight by Delegates.notNull<Int>()
    private var downX = 0F
    private var downY = 0F

    private val scroller by lazy { Scroller(context) }

    var slideListener: onSlideChangeListener ?= null


    override fun onFinishInflate() {
        super.onFinishInflate()
        contentView = getChildAt(0)
        menuView = getChildAt(1)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        contentWidth = measuredWidth
        contentHeight = measuredHeight
        menuWidth = menuView.measuredWidth
        menuHeight = menuView.measuredHeight
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        menuView.layout(contentWidth, 0, contentWidth + menuWidth, menuHeight)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = x
                downY = y
                if (slideListener != null) {
                    (slideListener as onSlideChangeListener).onMenuClick(this)
                }
            }

            MotionEvent.ACTION_MOVE -> {
                val dx = x - downX
                val dy = downY - y

                var disX = (scrollX - dx).toInt()
                if (disX < 0) {
                    disX = 0
                } else if (disX > menuWidth) {
                    disX = menuWidth
                }
                scrollTo(disX, scrollY)

                val moveX = abs(x - downX)
                val moveY = abs(y - downY)
                if (moveX > 10 && moveX > moveY) {
                    parent.requestDisallowInterceptTouchEvent(true)
                }

                downX = x
                downY = y
            }

            MotionEvent.ACTION_UP -> {
                if (scrollX < menuWidth / 2) {
                    closeMenu()
                } else {
                    openMenu()
                }
            }

        }
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        var intercept = false
        val x = ev.x
        val y = ev.y
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = x
                downY = y
            }

            MotionEvent.ACTION_MOVE -> {
                val moveX = Math.abs(x - downX)
                if (moveX > 10) {
                    intercept = true
                }
            }
        }

        return intercept
    }

    override fun computeScroll() {
        super.computeScroll()
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.currX, scroller.currY)
            invalidate()
        }
    }

    fun openMenu() {
        scroller.startScroll(scrollX, scrollY, menuWidth - scrollX, 0)
        invalidate()
        if (slideListener != null) {
            (slideListener as onSlideChangeListener).onMenuOpen(this)
        }
    }

    fun closeMenu() {
        scroller.startScroll(scrollX, scrollY, -scrollX, 0)
        invalidate()
        if (slideListener != null) {
            (slideListener as onSlideChangeListener).onMenuClose(this)
        }
    }

}