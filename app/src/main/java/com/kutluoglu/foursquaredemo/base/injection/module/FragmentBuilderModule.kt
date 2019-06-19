package com.kutluoglu.foursquaredemo.base.injection.module

import com.kutluoglu.foursquaredemo.main.features.venueDetail.ItemDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by F.K. on 2019-06-17.
 *
 */

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeItemDetailFragment() : ItemDetailFragment

}