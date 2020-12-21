package com.school.dailylife.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import com.school.dailylife.R
import com.school.dailylife.util.toast
import com.school.dailylife.view.fragment.dialog.WaitDialog
import com.school.dailylife.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {
    override val contentViewId: Int
        get() = R.layout.activity_register

    private val viewmodel by viewModels<UserViewModel>()
    private var dialog: DialogFragment? = null

    override fun initView(savedInstanceState: Bundle?) {
        iv_register_back.setOnClickListener { finish() }

        viewmodel.isRegisterSuccess.observe(this@RegisterActivity, {
            dismiss()
            if (it) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                toast("注册失败")
            }
        })

        btn_register.setOnClickListener {
            val username = et_register_username.text.toString()
            val psw = et_register_psw.text.toString()
            if (isUsernameAvalable(username)
                && isPassword(psw)
            ) {
                viewmodel.register(username, psw)
                dialog = WaitDialog()
                dialog?.show(supportFragmentManager, "register_wait")
            } else {
                toast("用户名或者密码不合格！")
            }
        }
    }

    private fun isUsernameAvalable(username: String): Boolean {
        return username.trim().length == 8
    }


    private fun isPassword(psw: String): Boolean {
        return psw.trim().length >= 6
    }

    override fun onDestroy() {
        super.onDestroy()
        dismiss()
    }

    private fun dismiss() {
        if (dialog?.isVisible ?: false) {
            dialog?.dismiss()
        }
    }
}