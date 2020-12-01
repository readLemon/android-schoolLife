package com.school.dailylife.util

import com.school.dailylife.bean.User

/**
 * Created by chenyang
 * on 20-11-25
 */
object CurrentUser {

    private var user: User ?= null


    fun getCurrentUser(): User{

        return this.user!!
    }

    fun setCurrentUser(user: User) {
        this.user = user
    }


}