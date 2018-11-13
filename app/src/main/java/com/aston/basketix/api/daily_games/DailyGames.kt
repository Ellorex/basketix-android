package com.aston.basketix.api.daily_games

import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

val current_dailygames  = Calendar.getInstance().time
val date_formated = current_dailygames.toString("yyyyMMdd")

val URL_DAILYGAMES = "https://api.mysportsfeeds.com/v2.0/pull/nba/current/date/$date_formated/games.json"

