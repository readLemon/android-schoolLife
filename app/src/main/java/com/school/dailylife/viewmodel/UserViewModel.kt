package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mredrock.runtogether.App
import com.school.dailylife.config.*
import com.school.dailylife.repository.UserRepository
import com.school.dailylife.util.CurrentUser
import com.school.dailylife.util.defaultSharedPrefrence
import com.school.dailylife.util.editor

/**
 * Created by chenyang
 * on 20-11-23
 */
class UserViewModel : BaseViewModel() {

    private val repo by lazy { UserRepository() }

    val isLoginSuccess by lazy { MutableLiveData<Boolean>() }
    val isRegisterSuccess by lazy { MutableLiveData<Boolean>() }


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
                    putString(CURRENT_USER_TOKEN, it.token)
                    putString(CURRENT_USER_AVATAR, it.avatarPic)
                    putString(CURRENT_USER_SIGNATURE, it.signature)
                    putString(CURRENT_USER_NiCKNAME, it.nickname)
                    putBoolean(CURRNT_USER_EXIST, true)
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