package com.example.appcatalog.data

import android.content.Context

class OnboardingPreferences(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("app_catalog_prefs", Context.MODE_PRIVATE)

    fun isOnboardingShown(): Boolean {
        return sharedPreferences.getBoolean("onboarding_shown", false)
    }

    fun setOnboardingShown(shown: Boolean) {
        sharedPreferences.edit()
            .putBoolean("onboarding_shown", shown)
            .apply()
    }
}