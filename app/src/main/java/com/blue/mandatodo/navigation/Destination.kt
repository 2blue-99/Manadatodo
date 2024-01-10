package com.blue.mandatodo.navigation

import com.blue.designsystem.icon.MandaIcon

enum class Destination(
    val selectedIcon: Int,
    val unSelectedIcon: Int,
    val iconTextId: String,
    val titleTextId: String,
) {
    DAILY(
        selectedIcon = MandaIcon.DailyBold,
        unSelectedIcon = MandaIcon.DailyNone,
        iconTextId = "Daily",
        titleTextId = "Daily"
    ),
    HISTORY(
        selectedIcon = MandaIcon.HistoryBold,
        unSelectedIcon = MandaIcon.HistoryNone,
        iconTextId = "History",
        titleTextId = "History"
    ),
    MANDALART(
        selectedIcon = MandaIcon.MandaBold,
        unSelectedIcon = MandaIcon.MandaNone,
        iconTextId = "Mandalart",
        titleTextId = "Mandalart"
    )
}