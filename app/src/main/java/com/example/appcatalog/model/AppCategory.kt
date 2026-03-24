package com.example.appcatalog.model

object AppCategory {
    const val FINANCE = "Финансы"
    const val TOOLS = "Инструменты"
    const val GAMES = "Игры"
    const val GOVERNMENT = "Государственные"
    const val TRANSPORT = "Транспорт"

    val all = listOf(
        FINANCE,
        TOOLS,
        GAMES,
        GOVERNMENT,
        TRANSPORT
    )
}