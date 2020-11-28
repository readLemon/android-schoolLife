package com.school.dailylife.view.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import com.school.dailylife.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override val contentViewId: Int
        get() = R.layout.activity_login

    override fun initView(savedInstanceState: Bundle?) {

        setLoginAnimation()

        btn_login.setOnClickListener {
            val username = et_login_username.text
            val psw = et_login_psw.text


            lav_login.visibility = View.VISIBLE
            lav_login.playAnimation()
        }


        tv_login_go_to_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    @SuppressLint("WrongConstant")
    private fun setLoginAnimation() {
        with(lav_login) {
            setAnimation("login_animation.json")
            repeatCount = 1
            speed = 2f
            repeatMode = Animation.REVERSE
            addAnimatorListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }

                override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {
                    tv_login_bg.visibility = View.VISIBLE
                    invalidate()
                }
            })
        }
    }


    override fun onStop() {
        super.onStop()
        lav_login.pauseAnimation()
        tv_login_bg.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        lav_login.cancelAnimation()

    }
}