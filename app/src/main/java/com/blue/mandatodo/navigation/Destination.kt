package com.blue.mandatodo.navigation

import com.blue.designsystem.icon.MandaIcon

enum class Destination(
    val selectedIcon: Int,
    val unSelectedIcon: Int,
    val titleTextId: String,
) {
    DAILY(
        selectedIcon = MandaIcon.DailyBold,
        unSelectedIcon = MandaIcon.DailyNone,
        titleTextId = "Daily"
    ),
    HISTORY(
        selectedIcon = MandaIcon.HistoryBold,
        unSelectedIcon = MandaIcon.HistoryNone,
        titleTextId = "History"
    ),
    MANDALART(
        selectedIcon = MandaIcon.MandaBold,
        unSelectedIcon = MandaIcon.MandaNone,
        titleTextId = "Mandalart"
    )
}