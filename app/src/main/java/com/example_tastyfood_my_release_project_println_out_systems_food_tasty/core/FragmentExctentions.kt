package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.core

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.activity.action_for_activity.interfaces.FavouriteFoodDb

fun Fragment.getActivityAboutFavouriteFoodSqlDatabase(): FavouriteFoodDb {
    return requireActivity() as FavouriteFoodDb
}

fun navigateToFragmentByAction(action: Int, fragment: Fragment) {
    NavHostFragment.findNavController(fragment).navigate(action)
}

fun navigateToFragmentByAction(action: Int, bundle: Bundle, fragment: Fragment) {
    NavHostFragment.findNavController(fragment).navigate(
        action, bundle
    )
}

fun Fragment.logD(str: String) {
    Log.d("MyLog", "$str")
}

fun Fragment.logE(str: String) {
    Log.e("MyLog", "$str")
}

fun Fragment.showLongToast(context: Context, msg: String) {
    Toast.makeText(
        context, msg, Toast.LENGTH_LONG
    ).show()
}