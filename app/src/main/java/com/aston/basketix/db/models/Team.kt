package com.aston.basketix.db.models

import com.aston.basketix.db.AppDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.annotation.Unique
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = AppDatabase::class)
class Team : BaseModel() {

    @Column
    @PrimaryKey(autoincrement = true)
    var id: Int = 0

    @Column
    @Unique
    var teamId: Int = 0

    @Column
    var isNBAFranchise: Boolean = false

    @Column
    var isAllStar: Boolean = false

    @Column
    var city: String? = null

    @Column
    var altCityName: String? = null

    @Column
    var fullName: String? = null

    @Column
    var tricode: String? = null

    @Column
    var nickname: String? = null

    @Column
    var urlName: String? = null

    @Column
    var confName: String? = null

    @Column
    var divName: String? = null
}