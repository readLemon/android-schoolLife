package com.school.dailylife.view.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.school.dailylife.R
import com.school.dailylife.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override val contentViewId: Int
        get() = R.layout.activity_login

    private val viewmodel by viewModels<UserViewModel>()

    override fun initView(savedInstanceState: Bundle?) {

        setLoginAnimation()
        setObserVer()

        btn_login.setOnClickListener {
            val username = et_login_username.text.toString()
            val psw = et_login_psw.text.toString()

            viewmodel.login(username, psw)
            lav_login.visibility = View.VISIBLE
            lav_login.playAnimation()
        }


        tv_login_go_to_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun setObserVer() {
        viewmodel.isLoginSuccess.observe(this@LoginActivity, {
            if (it) {
                Log.e(TAG,"登录成功")
                if (lav_login.isAnimating) {
                    lav_login.cancelAnimation()
                    goToMainActivity()
                }
            }
        })
    }

    private fun goToMainActivity () {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
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
                    goToMainActivity()
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

    companion object{
        val TAG = "LoginActivity"
    }
}