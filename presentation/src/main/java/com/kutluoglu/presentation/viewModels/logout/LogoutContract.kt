package com.kutluoglu.presentation.viewModels.logout

import androidx.lifecycle.LiveData
import com.kutluoglu.presentation.resource.Resource

/**
 * Created by F.K. on 2019-06-19.
 *
 */

interface LogoutContract {
    fun getLogoutLiveData() : LiveData<Resource<Boolean>>
    fun logout()
    fun logoutCompleted()
}