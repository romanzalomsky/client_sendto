package com.zalomsky.client_sendto

import androidx.annotation.StringRes
import com.zalomsky.client_sendto.MainDestinations.BASE_ROUTE
import com.zalomsky.client_sendto.common.accountIcon
import com.zalomsky.client_sendto.common.clientBaseIcon
import com.zalomsky.client_sendto.common.sendsIcon
import com.zalomsky.client_sendto.common.tasksIcon

object MainDestinations {

    const val BASE_ROUTE = "base"
    const val REGISTRATION_ROUTE = "registration"
    const val AUTH_ROUTE = "auth"
    const val START_ROUTE = "start"
}

enum class BaseSections(
    @StringRes val title: Int,
    val icon: Int,
    val route: String,
) {
    BASE(
        R.string.client_base,
        clientBaseIcon,
        "$BASE_ROUTE/base"
    ),
    TASKS(
        R.string.tasks,
        tasksIcon,
        "$BASE_ROUTE/tasks"
    ),
    SENDS(
        R.string.sends,
        sendsIcon,
        "$BASE_ROUTE/sends"
    ),
    ACCOUNT(
        R.string.account,
        accountIcon,
        "$BASE_ROUTE/account"
    )
}