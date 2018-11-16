package com.aston.basketix.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.aston.basketix.R
import com.aston.basketix.db.models.Team
import com.aston.basketix.ui.ranking.RankingFragment
import com.aston.basketix.ui.favorite.FavoriteFragment
import com.aston.basketix.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.provider.ContactsContract.CommonDataKinds.Organization
import android.util.Log
import com.aston.basketix.db.models.Team_Table
import com.raizlabs.android.dbflow.sql.language.SQLite




class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, HomeFragment())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, FavoriteFragment())
                        .commit()
                supportFragmentManager
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_ranking -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, RankingFragment())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
