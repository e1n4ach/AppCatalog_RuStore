package com.example.appcatalog.navigation

object AppDestinations {
    const val STORE = "store"
    const val CATEGORIES = "categories"
    const val SEARCH = "search"
    const val DETAILS = "details"
    const val VIEWER = "viewer"

    const val APP_ID_ARG = "appId"
    const val SCREENSHOT_INDEX_ARG = "screenshotIndex"

    const val DETAILS_ROUTE = "$DETAILS/{$APP_ID_ARG}"
    const val VIEWER_ROUTE = "$VIEWER/{$APP_ID_ARG}/{$SCREENSHOT_INDEX_ARG}"

    fun detailsRoute(appId: Int): String = "$DETAILS/$appId"
    fun viewerRoute(appId: Int, screenshotIndex: Int): String = "$VIEWER/$appId/$screenshotIndex"
}