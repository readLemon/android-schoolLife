package com.school.dailylife.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mredrock.runtogether.App
import com.school.dailylife.config.CORRENT_USER_ACCOUNT_NUM
import com.school.dailylife.config.CORRENT_USER_PSW
import com.school.dailylife.repository.UserRepository
import com.school.dailylife.util.CurrentUser
import com.school.dailylife.util.defaultSharedPrefrence
import com.school.dailylife.util.editor
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by chenyang
 * on 20-11-23
 */
class UserViewModel : BaseViewModel() {

    private val repo by lazy { UserRepository() }

    val isLoginSuccess = MutableLiveData(false)
    val isRegisterSuccess = MutableLiveData(false)


    fun login(username: String, psw: String, isRememberUser: Boolean) {
        repo.login(username, psw).subscribe({
            CurrentUser.setCurrentUser(it)
            CurrentUser.getCurrentUser().username = username
            CurrentUser.getCurrentUser().psw = psw
            val sp = App.context.defaultSharedPrefrence

            if (isRememberUser) {
                sp.editor {
                    putString(CORRENT_USER_ACCOUNT_NUM, username)
                    putString(CORRENT_USER_PSW, psw)
                }
            } else {
                sp.editor {
                    putString(CORRENT_USER_ACCOUNT_NUM, "")
                    putString(CORRENT_USER_PSW, "")
                }
            }
            isLoginSuccess.value = true

        }, {
            isLoginSuccess.value = false
            it.printStackTrace()
        }).lifeCycle()
    }

    fun register(username: String, psw: String) {
        repo.register(username, psw).subscribe({
            isRegisterSuccess.value = true
        }, {
            isRegisterSuccess.value = false
            it.printStackTrace()
        }).lifeCycle()
    }


}