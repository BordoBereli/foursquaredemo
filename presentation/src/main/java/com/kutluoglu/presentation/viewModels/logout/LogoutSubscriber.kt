package com.kutluoglu.presentation.viewModels.logout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kutluoglu.presentation.resource.Resource
import com.kutluoglu.presentation.resource.ResourceState
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by F.K. on 2019-06-18.
 *
 */

val _logoutUserLiveData : MutableLiveData<Resource<Boolean>> = MutableLiveData()

val logoutUserLiveData : LiveData<Resource<Boolean>>
    get() = _logoutUserLiveData

open class LogoutSubscriber : DisposableSingleObserver<Boolean>() {
    override fun onSuccess(logout: Boolean) {
        handleSuccessCase(logout)
    }

    override fun onError(error: Throwable) {
        handleErrorCase(error)
    }

    private fun handleSuccessCase(logout: Boolean) {
        _logoutUserLiveData.postValue(Resource(ResourceState.SUCCESS, logout, null))
    }

    private fun handleErrorCase(error: Throwable) {
        _logoutUserLiveData.postValue(Resource(ResourceState.ERROR, null, error.message))
    }
}