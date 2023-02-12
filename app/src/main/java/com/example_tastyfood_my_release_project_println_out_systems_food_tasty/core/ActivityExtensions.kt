package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.core

import android.app.Activity
import android.net.ConnectivityManager
import android.util.Log

fun Activity.logD(str: String) {
    Log.d("MyLog", "$str")
}

fun Activity.logE(str: String) {
    Log.e("MyLog", "$str")
}