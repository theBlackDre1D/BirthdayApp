package com.g3.birthdayapp.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.navigateSafe(directions: NavDirections) {
    val resId = directions.actionId
    val action = currentDestination?.getAction(resId)
    if (action != null) navigate(directions)
}