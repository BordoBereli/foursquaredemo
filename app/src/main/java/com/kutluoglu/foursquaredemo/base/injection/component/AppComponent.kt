package com.kutluoglu.foursquaredemo.base.injection.component

import android.app.Application
import com.kutluoglu.foursquaredemo.main.FoursquareDemoApp
import com.kutluoglu.foursquaredemo.base.injection.scopes.PerApplication
import com.kutluoglu.foursquaredemo.base.injection.module.AppModule
import com.kutluoglu.foursquaredemo.base.injection.module.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by F.K. on 2019-06-17.
 *
 */

@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class, // Because of using AndroidX fragment
        AppModule::class,
        MainActivityModule::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }

    fun inject(fsApp: FoursquareDemoApp)
}