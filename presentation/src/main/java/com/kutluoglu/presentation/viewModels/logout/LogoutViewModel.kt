package com.kutluoglu.presentation.viewModels.logout

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kutluoglu.domain.interactor.RemoveUserUseCase
import com.kutluoglu.presentation.resource.Resource
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-19.
 *
 */

open class LogoutViewModel @Inject constructor (
        private val logoutUseCase: RemoveUserUseCase
) : ViewModel(), LogoutContract {

    override fun logout() {
        return logoutUseCase.execute(LogoutSubscriber())
    }

    override fun getLogoutLiveData(): LiveData<Resource<Boolean>> {
        return logoutUserLiveData
    }

    override fun logoutCompleted() {
        _logoutUserLiveData.value = null
    }
    override fun onCleared() {
        logoutUseCase.dispose()
        super.onCleared()
    }
}