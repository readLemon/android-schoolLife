package com.school.dailylife.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.school.dailylife.R
import com.school.dailylife.bean.ChatMsgBean
import com.school.dailylife.bean.MSG_TYPE
import com.school.dailylife.util.toast
import com.school.dailylife.view.adapter.CommonRecyclerAdapter
import com.school.dailylife.viewmodel.BotChatViewModel
import kotlinx.android.synthetic.main.activity_bot.*
import kotlinx.android.synthetic.main.item_rv_chat.view.*

class BotActivity : BaseActivity() {
    override val contentViewId: Int
        get() = R.layout.activity_bot
    private val chatData = mutableListOf<ChatMsgBean>()
    private var adapter: CommonRecyclerAdapter<ChatMsgBean>? = null
    private val chatVm by viewModels<BotChatViewModel>()

    override fun observeVM() {
        chatVm.responseMsg.observe(this, {
            addChatBean(it)
        })
    }

    override fun initView(savedInstanceState: Bundle?) {

        adapter = CommonRecyclerAdapter(
            layoutId = R.layout.item_rv_chat,
            datas = chatData,
            bindHolder = { bean ->
                if (bean.msgPosition == MSG_TYPE.LEFT) {
                    this.tv_chat_right.visibility = View.GONE
                    this.tv_chat_left.visibility = View.VISIBLE
                    this.tv_chat_left.text = bean.msg
                } else {
                    this.tv_chat_left.visibility = View.GONE
                    this.tv_chat_right.visibility = View.VISIBLE
                    this.tv_chat_right.text = bean.msg
                }
            }
        )
        rv_chat_page.adapter = adapter

        btn_send_msg.setOnClickListener {
            val text = et_bottom_input.text.toString()
            if (text.isNotEmpty()) {
                val chatBean = ChatMsgBean(MSG_TYPE.RIGHT, text)
                addChatBean(chatBean)
                et_bottom_input.setText("")
                chatVm.chat(text)
            } else {
                toast("请输入合法信息")
            }
        }
    }

    private fun fakeData() {
        Thread {
            repeat(100) {
                if (it and 1 == 0) {
                    val bean = ChatMsgBean(MSG_TYPE.LEFT, "xxxxxxxxxxx$it")

                    addChatBean(bean)
                    Thread.sleep(1 * 1000)
                } else {
                    val bean = ChatMsgBean(MSG_TYPE.RIGHT, "xxxxxxxxxxx$it")
                    addChatBean(bean)
                    Thread.sleep(2 * 1000)

                }
            }
        }.start()

    }

    private fun addChatBean(bean: ChatMsgBean?) {
        bean ?: return
        rv_chat_page.post {
            chatData.add(bean)
            adapter?.notifyDataSetChanged()
            rv_chat_page.scrollToPosition(chatData.size - 1)
        }
    }
}