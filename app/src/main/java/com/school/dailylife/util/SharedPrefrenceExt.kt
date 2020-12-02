package com.school.dailylife.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by chenyang
 * on 20-12-2
 */

val DEFAULT_SHARED_PREFRENCE_NAME = "school_life"

val Context.defaultSharedPrefrence
    get() = privateSharedPrefrences(DEFAULT_SHARED_PREFRENCE_NAME)

fun Context.privateSharedPrefrences(name: String) = getSharedPreferences(name, Context.MODE_PRIVATE)

fun SharedPreferences.editor(editor: SharedPreferences.Editor.() -> Unit) =
    edit().apply(editor).apply()