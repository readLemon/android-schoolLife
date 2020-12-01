package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.school.dailylife.repository.UserRepository
import com.school.dailylife.util.CurrentUser

/**
 * Created by chenyang
 * on 20-11-23
 */
class UserViewModel: BaseViewModel() {

    private val repo by lazy { UserRepository() }

//    val isLoginSuccess = MutableLiveData(false)
    val isLoginSuccess = MutableLiveData(false)
    val isRegisterSuccess = MutableLiveData(false)

    fun login(username: String, psw: String) {
        repo.login(username, psw).subscribe({
            CurrentUser.setCurrentUser(it)
            isLoginSuccess.value = true
        },{
            isLoginSuccess.value = false
            it.printStackTrace()
        }).lifeCycle()
    }

    fun register(username: String, psw: String) {
        repo.register(username, psw).subscribe({
            isRegisterSuccess.value = true
        },{
            isRegisterSuccess.value = false
            it.printStackTrace()
        }).lifeCycle()
    }


}