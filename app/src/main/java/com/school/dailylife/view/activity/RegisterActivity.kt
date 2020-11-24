package com.school.dailylife.view.activity

import android.os.Bundle
import com.school.dailylife.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {
    override val contentViewId: Int
        get() = R.layout.activity_register

    override fun initView(savedInstanceState: Bundle?) {
        iv_register_back.setOnClickListener { finish() }

        btn_register.setOnClickListener {
            val username = et_register_username.text
            val psw = et_register_psw.text

        }
    }

}