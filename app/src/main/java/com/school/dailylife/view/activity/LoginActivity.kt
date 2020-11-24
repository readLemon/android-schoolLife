package com.school.dailylife.view.activity

import android.content.Intent
import android.os.Bundle
import com.school.dailylife.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override val contentViewId: Int
        get() = R.layout.activity_login

    override fun initView(savedInstanceState: Bundle?) {
        btn_login.setOnClickListener {
            val username = et_login_username.text
            val psw = et_login_psw.text

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }


        tv_login_go_to_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}