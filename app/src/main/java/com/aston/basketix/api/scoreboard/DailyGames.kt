package com.aston.basketix.api.scoreboard

import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

val current_dailygames  = Calendar.getInstance().time
val date_formated = current_dailygames.toString("yyyyMMdd")

val URL_DAILYGAMES = "http://data.nba.net/10s/prod/v1/$date_formated/scoreboard.json"

