package com.kutluoglu.presentation.viewModels.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kutluoglu.presentation.resource.Resource
import com.kutluoglu.presentation.resource.ResourceState
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by F.K. on 2019-06-18.
 *
 */

val _isUserLoginLiveData : MutableLiveData<Resource<Boolean>> = MutableLiveData()

val isUserLoginLiveData : LiveData<Resource<Boolean>>
    get() = _isUserLoginLiveData

open class IsUserLoginSubscriber : DisposableSingleObserver<Boolean>() {
    override fun onSuccess(isLogin: Boolean) {
        handleSuccessCase(isLogin)
    }

    override fun onError(error: Throwable) {
        handleErrorCase(error)
    }

    private fun handleSuccessCase(isLogin: Boolean) {
        _isUserLoginLiveData.postValue(Resource(ResourceState.SUCCESS, isLogin, null))
    }

    private fun handleErrorCase(error: Throwable) {
        _isUserLoginLiveData.postValue(Resource(ResourceState.ERROR, null, error.message))
    }
}