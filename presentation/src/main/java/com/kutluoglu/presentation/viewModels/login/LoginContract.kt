package com.kutluoglu.presentation.viewModels.login

import androidx.lifecycle.LiveData
import com.kutluoglu.presentation.models.login.LoggedInUser
import com.kutluoglu.presentation.resource.Resource

/**
 * Created by F.K. on 2019-06-18.
 *
 */

interface LoginContract {
    fun login(userId: String)
    fun isUserLogin()
    fun getLoginLiveData() : LiveData<Resource<LoggedInUser>>
    fun getIsUserLoginLiveData() : LiveData<Resource<kotlin.Boolean>>
    fun loginSuccess()
    fun loginCompleted()
}