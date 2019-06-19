package com.kutluoglu.foursquaredemo.base

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.kutluoglu.foursquaredemo.utils.extensions.showProgressSpinner
import com.kutluoglu.presentation.viewModels.FoursquareViewModelFactory
import javax.inject.Inject

open class BaseActivity : AppCompatActivity(), DrawerLocker {

    @Inject
    lateinit var viewModelFactory: FoursquareViewModelFactory

    private var spinnerProgress: AlertDialog? = null

    override fun setDrawerEnabled(enabled: Boolean) {
        val lockMode = if (enabled) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED
//        drawer_layout.setDrawerLockMode(lockMode)
    }

    /*fun setBadgeCount(icon: LayerDrawable, count: Int) {
        val reuse = icon.findDrawableByLayerId(R.id.ic_badge)
        val badge = if (reuse != null && reuse is BadgeDrawable) reuse
        else BadgeDrawable(applicationContext)

        badge.setCount(count.toString())
        icon.mutate()
        icon.setDrawableByLayerId(R.id.ic_badge, badge)
    }*/

    fun showSpinner() {
        if(spinnerProgress == null) {
            spinnerProgress = showProgressSpinner()
        }

        spinnerProgress?.let {
            if(!it.isShowing) it.show()
        }

    }

    fun dismissSpinner() {
        spinnerProgress?.let {
            if(it.isShowing) it.dismiss()
        }
    }


    override fun onDestroy() {
        dismissSpinner()
        super.onDestroy()
    }
}
