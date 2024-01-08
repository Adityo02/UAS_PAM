package com.example.outdoor.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.outdoor.ui.halaman.DetailScreen
import com.example.outdoor.ui.halaman.EntryPesananScreen
import com.example.outdoor.ui.halaman.HomeScreen
import com.example.outdoor.ui.halaman.ItemEditScreen
import com.example.outdoor.ui.halaman.RouteDetail
import com.example.outdoor.ui.halaman.RouteEdit
import com.example.outdoor.ui.halaman.RouteEntry
import com.example.outdoor.ui.halaman.RouteHome

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdventureAppBar(
    title: String,
    bisaNavigasiBack: Boolean,
    navigasiUp: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (bisaNavigasiBack) {
                IconButton(onClick = navigasiUp) {
                    Icons.Default.ArrowBack

                }
            }
        }
    )
}

@Composable
fun AdventureMountain(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = RouteHome.route) {
        composable(RouteHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(RouteEntry.route) },
                onDetailClick = { itemId -> navController.navigate("${RouteDetail.route}/$itemId") }
            )
        }
        composable(RouteEntry.route) {
            EntryPesananScreen(navigateBack = { navController.popBackStack() })
        }
        composable(
            RouteDetail.routeWithArgs,
            arguments = listOf(
                navArgument(RouteDetail.sewaIdArg) {
                    type = NavType.IntType
                },
            ),
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(RouteDetail.sewaIdArg)
            itemId?.let {
                DetailScreen(
                    navigateToEditItem = { navController.navigate("${RouteEdit.route}/$it") },
                    navigateBack = { navController.popBackStack() })
            }
        }
        composable(
            RouteEdit.routeWithArgs,
            arguments = listOf(navArgument(RouteEdit.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })

        }
    }
}