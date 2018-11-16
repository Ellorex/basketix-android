package com.aston.basketix.db.models

import com.aston.basketix.api.teams.Standard
import com.aston.basketix.db.AppDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.annotation.Unique
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = AppDatabase::class, name = "Team")
class Team(standardTeam: Standard?) : BaseModel() {
    var standardTeam = null

    constructor() : this(standardTeam = Standard()) {
        println("Constructor")
        this.standardTeam = standardTeam
    }

    @Column
    @PrimaryKey(autoincrement = true)
    var id: Int = 0

    @Column
    @Unique
    var teamId: String? = standardTeam?.teamId

    @Column
    var isNBAFranchise: Boolean? = standardTeam?.isNBAFranchise

    @Column
    var isAllStar: Boolean? = standardTeam?.isAllStar

    @Column
    var city: String? = standardTeam?.city

    @Column
    var altCityName: String? = standardTeam?.altCityName

    @Column
    var fullName: String? = standardTeam?.fullName

    @Column
    var tricode: String? = standardTeam?.tricode

    @Column
    var nickname: String? = standardTeam?.nickname

    @Column
    var urlName: String? = standardTeam?.urlName

    @Column
    var confName: String? = standardTeam?.confName

    @Column
    var divName: String? = standardTeam?.divName
}