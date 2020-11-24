package com.school.dailylife.viewmodel

import androidx.lifecycle.MutableLiveData
import com.school.dailylife.repository.UserRepository

/**
 * Created by chenyang
 * on 20-11-23
 */
class UserViewModel: BaseViewModel() {

    val repo by lazy { UserRepository() }

    val isLoginSuccess = MutableLiveData(false)
    val isRegisterSuccess = MutableLiveData(false)

    fun login(username: String, psw: String) {
        repo.login(username, psw).subscribe({

       isLoginSuccess.value = true
        },{
            isLoginSuccess.value = false
        }).lifeCycle()
    }

    fun register(username: String, psw: String) {
        repo.register(username, psw).subscribe({
            isRegisterSuccess.value = true
        },{
            isRegisterSuccess.value = false
        }).lifeCycle()
    }


}