package com.aston.basketix.db

import com.raizlabs.android.dbflow.annotation.Database


@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
object AppDatabase {
    const val NAME: String = "NBA"
    const val VERSION: Int = 1
}
