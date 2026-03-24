package com.example.appcatalog.data

import com.example.appcatalog.R
import com.example.appcatalog.model.AppCategory
import com.example.appcatalog.model.AppItem

object MockData {

    val apps = listOf(
        AppItem(
            id = 1,
            name = "VK Pay",
            shortDescription = "Платежи и переводы",
            fullDescription = "Удобное приложение для переводов, оплаты услуг и контроля финансов.",
            category = AppCategory.FINANCE,
            developer = "VK",
            ageRating = "12+",
            iconResId = R.drawable.ic_launcher_foreground,
            screenshots = listOf("purple", "teal", "orange"),
            isPopular = true
        ),
        AppItem(
            id = 2,
            name = "Мои документы",
            shortDescription = "Госуслуги и документы",
            fullDescription = "Приложение для доступа к государственным сервисам и цифровым документам.",
            category = AppCategory.GOVERNMENT,
            developer = "ГосТех",
            ageRating = "0+",
            iconResId = R.drawable.ic_launcher_foreground,
            screenshots = listOf("teal", "purple"),
            isPopular = true
        ),
        AppItem(
            id = 3,
            name = "City Transport",
            shortDescription = "Маршруты и транспорт",
            fullDescription = "Поиск маршрутов, расписание транспорта и отслеживание поездок по городу.",
            category = AppCategory.TRANSPORT,
            developer = "Urban Soft",
            ageRating = "6+",
            iconResId = R.drawable.ic_launcher_foreground,
            screenshots = listOf("orange", "teal"),
            isPopular = false
        ),
        AppItem(
            id = 4,
            name = "Notes Pro",
            shortDescription = "Заметки и списки",
            fullDescription = "Приложение для заметок, списков дел и повседневной организации задач.",
            category = AppCategory.TOOLS,
            developer = "Productive Lab",
            ageRating = "0+",
            iconResId = R.drawable.ic_launcher_foreground,
            screenshots = listOf("purple", "orange"),
            isPopular = true
        ),
        AppItem(
            id = 5,
            name = "Sky Battle",
            shortDescription = "Аркадная мобильная игра",
            fullDescription = "Динамичная игра с уровнями, бонусами и соревнованием за лучший счет.",
            category = AppCategory.GAMES,
            developer = "PlayStorm",
            ageRating = "8+",
            iconResId = R.drawable.ic_launcher_foreground,
            screenshots = listOf("orange", "purple", "teal"),
            isPopular = true
        )
    )
}