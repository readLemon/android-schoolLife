package com.school.dailylife.util

import com.school.dailylife.App
import com.school.dailylife.bean.User
import com.school.dailylife.config.*

/**
 * Created by chenyang
 * on 20-11-25
 */
object CurrentUser {

    var user: User? = null


    fun getCurrentUser(): User {
        if (user == null
            && App.context.defaultSharedPrefrence.getBoolean(CURRNT_USER_EXIST, false)
        ) {
            setCurrentUser(
                User(
                    App.context.defaultSharedPrefrence.getString(CURRENT_USER_AVATAR, "") ?: "",
                    App.context.defaultSharedPrefrence.getString(CURRENT_USER_NiCKNAME, "") ?: "",
                    App.context.defaultSharedPrefrence.getString(CURRENT_USER_SIGNATURE, "") ?: "",
                    App.context.defaultSharedPrefrence.getString(CURRENT_USER_TOKEN, "") ?: ""
                )
            )
        }
        return user!!
    }

    fun setCurrentUser(user: User) {
        this.user = user
    }


}