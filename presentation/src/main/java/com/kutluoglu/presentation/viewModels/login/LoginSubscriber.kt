package com.kutluoglu.presentation.viewModels.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kutluoglu.domain.model.User
import com.kutluoglu.presentation.mapper.UserMapper
import com.kutluoglu.presentation.models.login.LoggedInUser
import com.kutluoglu.presentation.resource.Resource
import com.kutluoglu.presentation.resource.ResourceState
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by F.K. on 2019-06-18.
 *
 */

val _loginUserLiveData : MutableLiveData<Resource<LoggedInUser>> = MutableLiveData()

val loginUserLiveData : LiveData<Resource<LoggedInUser>>
    get() = _loginUserLiveData

open class LoginSubscriber (private val userMapper: UserMapper) : DisposableSingleObserver<User>() {
    override fun onSuccess(user: User) {
        handleSuccessCase(user)
    }

    override fun onError(error: Throwable) {
        handleErrorCase(error)
    }

    private fun handleSuccessCase(user: User) {
        _loginUserLiveData.postValue(Resource(ResourceState.SUCCESS, userMapper.mapToView(user), null))
    }

    private fun handleErrorCase(error: Throwable) {
        _loginUserLiveData.postValue(Resource(ResourceState.ERROR, null, error.message))
    }
}