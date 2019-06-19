package com.kutluoglu.foursquaredemo.base.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kutluoglu.foursquaredemo.base.injection.ViewModelKey
import com.kutluoglu.presentation.viewModels.FoursquareViewModelFactory
import com.kutluoglu.presentation.viewModels.getVenues.GetVenuesViewModel
import com.kutluoglu.presentation.viewModels.login.LoginViewModel
import com.kutluoglu.presentation.viewModels.logout.LogoutViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by F.K. on 2019-06-17.
 *
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: FoursquareViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GetVenuesViewModel::class)
    abstract fun bindVenuesViewModel(getVenuesViewModel: GetVenuesViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LogoutViewModel::class)
    abstract fun bindLogoutViewModel(logoutViewModel: LogoutViewModel) : ViewModel

}