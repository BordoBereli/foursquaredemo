package com.kutluoglu.foursquaredemo.base.injection.module


import com.kutluoglu.foursquaredemo.login.LoginActivity
import com.kutluoglu.foursquaredemo.main.features.venueDetail.ItemDetailActivity
import com.kutluoglu.foursquaredemo.main.features.venueList.ItemListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module used to provide dependencies at an activity-level.
 *
 */

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeItemListActivity() : ItemListActivity


    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeItemDetailActivity() : ItemDetailActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeLoginActivity() : LoginActivity
}
