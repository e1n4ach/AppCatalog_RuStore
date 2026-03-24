package com.example.appcatalog.model

data class AppItem(
    val id: Int,
    val name: String,
    val shortDescription: String,
    val fullDescription: String,
    val category: String,
    val developer: String,
    val ageRating: String,
    val iconResId: Int,
    val screenshots: List<String>,
    val isPopular: Boolean = false
)