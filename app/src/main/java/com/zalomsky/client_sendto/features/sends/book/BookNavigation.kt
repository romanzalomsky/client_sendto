package com.zalomsky.client_sendto.features.sends.book

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zalomsky.client_sendto.MainDestinations
import com.zalomsky.client_sendto.defaultNavOptions

fun NavController.navigateToBookScreen() =
    navigate(MainDestinations.BOOK_ROUTE, defaultNavOptions())

fun NavController.navigateToAddBookScreen() =
    navigate(MainDestinations.ADD_BOOK_ROUTE, defaultNavOptions())

fun NavController.navigateToEditBookScreen(bookId: String) =
    navigate("${MainDestinations.EDIT_BOOK_ROUTE}/$bookId")

fun NavGraphBuilder.bookScreen(
    upPress: () -> Unit,
    navController: NavHostController
){
    composable(MainDestinations.BOOK_ROUTE){
        BookScreen(
            onBackPressed = upPress,
            onBookAdd = {
                navController.navigateToAddBookScreen()
            },
            onBookEdit = { bookId ->
                navController.navigateToEditBookScreen(bookId?: "")
            }
        )
    }
}

fun NavGraphBuilder.addBookScreen(upPress: () -> Unit){
    composable(MainDestinations.ADD_BOOK_ROUTE){
        AddBookScreen(
            onBackPressed = upPress
        )
    }
}

fun NavGraphBuilder.editBookScreen(upPress: () -> Unit){
    composable(
        route = "${MainDestinations.EDIT_BOOK_ROUTE}/{id}",
        arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) {
        EditBookScreen(
            bookId = it.arguments?.getString("id"),
            onBackPressed = upPress
        )
    }
}