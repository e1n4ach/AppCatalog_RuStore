package com.example.appcatalog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appcatalog.data.MockData
import com.example.appcatalog.data.OnboardingPreferences
import com.example.appcatalog.model.StoreUiState
import com.example.appcatalog.navigation.AppDestinations
import com.example.appcatalog.screens.categories.CategoriesScreen
import com.example.appcatalog.screens.details.DetailsScreen
import com.example.appcatalog.screens.onboarding.OnboardingScreen
import com.example.appcatalog.screens.search.SearchScreen
import com.example.appcatalog.screens.store.StoreScreen
import com.example.appcatalog.screens.viewer.ScreenshotViewerScreen
import com.example.appcatalog.ui.theme.AppCatalogTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onboardingPreferences = OnboardingPreferences(this)

        setContent {
            AppCatalogTheme {
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppRoot(
                        onboardingPreferences = onboardingPreferences,
                        onInstallClick = {
                            Toast.makeText(
                                this,
                                "Установка пока не реализована",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AppRoot(
    onboardingPreferences: OnboardingPreferences,
    onInstallClick: () -> Unit
) {
    var isOnboardingShown by remember {
        mutableStateOf(onboardingPreferences.isOnboardingShown())
    }

    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    var storeUiState by remember { mutableStateOf<StoreUiState>(StoreUiState.Loading) }
    var isRefreshing by remember { mutableStateOf(false) }

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    fun loadStoreData(simulateError: Boolean = false) {
        scope.launch {
            if (!isRefreshing) {
                storeUiState = StoreUiState.Loading
            }

            delay(1200)

            if (simulateError) {
                storeUiState = StoreUiState.Error("Не удалось загрузить приложения")
            } else {
                val apps = if (selectedCategory == null) {
                    MockData.apps
                } else {
                    MockData.apps.filter { it.category == selectedCategory }
                }

                storeUiState = if (apps.isEmpty()) {
                    StoreUiState.Empty
                } else {
                    StoreUiState.Success(apps)
                }
            }

            isRefreshing = false
        }
    }

    LaunchedEffect(isOnboardingShown, selectedCategory) {
        if (isOnboardingShown) {
            loadStoreData()
        }
    }

    if (!isOnboardingShown) {
        OnboardingScreen(
            onContinueClick = {
                onboardingPreferences.setOnboardingShown(true)
                isOnboardingShown = true
            }
        )
    } else {
        NavHost(
            navController = navController,
            startDestination = AppDestinations.STORE
        ) {
            composable(AppDestinations.STORE) {
                StoreScreen(
                    uiState = storeUiState,
                    selectedCategory = selectedCategory,
                    isRefreshing = isRefreshing,
                    onRefresh = {
                        isRefreshing = true
                        loadStoreData()
                    },
                    onCategoriesClick = {
                        navController.navigate(AppDestinations.CATEGORIES)
                    },
                    onSearchClick = {
                        navController.navigate(AppDestinations.SEARCH)
                    },
                    onClearFilterClick = {
                        selectedCategory = null
                    },
                    onRetryClick = {
                        loadStoreData()
                    },
                    onAppClick = { appId ->
                        navController.navigate(AppDestinations.detailsRoute(appId))
                    }
                )
            }

            composable(AppDestinations.CATEGORIES) {
                CategoriesScreen(
                    apps = MockData.apps,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onCategoryClick = { category ->
                        selectedCategory = category
                        navController.popBackStack()
                    }
                )
            }

            composable(AppDestinations.SEARCH) {
                SearchScreen(
                    query = searchQuery,
                    apps = MockData.apps,
                    popularApps = MockData.apps.filter { it.isPopular },
                    onQueryChange = { searchQuery = it },
                    onBackClick = {
                        searchQuery = ""
                        navController.popBackStack()
                    },
                    onAppClick = { appId ->
                        navController.navigate(AppDestinations.detailsRoute(appId))
                    }
                )
            }

            composable(
                route = AppDestinations.DETAILS_ROUTE,
                arguments = listOf(
                    navArgument(AppDestinations.APP_ID_ARG) {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val appId = backStackEntry.arguments?.getInt(AppDestinations.APP_ID_ARG)
                val app = MockData.apps.find { it.id == appId }

                app?.let {
                    DetailsScreen(
                        app = it,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onInstallClick = onInstallClick,
                        onScreenshotClick = { index ->
                            navController.navigate(
                                AppDestinations.viewerRoute(it.id, index)
                            )
                        }
                    )
                }
            }

            composable(
                route = AppDestinations.VIEWER_ROUTE,
                arguments = listOf(
                    navArgument(AppDestinations.APP_ID_ARG) {
                        type = NavType.IntType
                    },
                    navArgument(AppDestinations.SCREENSHOT_INDEX_ARG) {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val appId = backStackEntry.arguments?.getInt(AppDestinations.APP_ID_ARG)
                val screenshotIndex =
                    backStackEntry.arguments?.getInt(AppDestinations.SCREENSHOT_INDEX_ARG) ?: 0

                val app = MockData.apps.find { it.id == appId }

                app?.let {
                    ScreenshotViewerScreen(
                        screenshots = it.screenshots,
                        startIndex = screenshotIndex,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}