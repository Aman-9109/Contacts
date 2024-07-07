package com.example.contacts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.contacts.screens.addContact.AddContact
import com.example.contacts.screens.detail.DetailScreen
import com.example.contacts.screens.home.Home

@Composable
fun NavigationScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home) {
        composable<Routes.Home> {
            Home(navController = navController)
        }
        composable<Routes.Detail> {
            val args = it.toRoute<Routes.Detail>()
            DetailScreen(navController = navController, contactId = args.contactId)
        }
        composable<Routes.AddContact> {
            AddContact(navController = navController)
        }

    }


}
