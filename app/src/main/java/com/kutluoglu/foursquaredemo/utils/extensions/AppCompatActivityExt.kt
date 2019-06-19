package com.kutluoglu.foursquaredemo.utils.extensions

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.kutluoglu.foursquaredemo.base.BaseActivity
import com.kutluoglu.foursquaredemo.utils.ProgressSpinner

/**
 * Created by F.K. on 2019-06-17.
 *
 */

/**
 * Actionbar Utils
 */
fun AppCompatActivity.setupActionbar(toolbar: Toolbar) {
    setSupportActionBar(toolbar)
}

/*
fun AppCompatActivity.setToogle(drawerLayout: DrawerLayout, toolbar: Toolbar) {
    val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close )
    drawerLayout.addDrawerListener(toggle)
    toggle.syncState()
}*/

/**
 * Show Spinner Progress Dialog
 */
fun BaseActivity.showProgressSpinner(): AlertDialog = ProgressSpinner(
        this
).create()
