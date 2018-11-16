package com.aston.basketix.api.scoreboard

class Games {

    var startTimeEastern: String? = null
    var arena: Arena? = null
    var vTeam: Team? = null
    var hTeam: Team? = null

    override fun toString(): String {
        return vTeam?.team+" vs "+hTeam?.team
    }
}
