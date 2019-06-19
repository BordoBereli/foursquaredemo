package com.kutluoglu.presentation.viewModels.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kutluoglu.domain.interactor.GetUserUseCase
import com.kutluoglu.domain.interactor.IsUserLoginUseCase
import com.kutluoglu.presentation.mapper.UserMapper
import com.kutluoglu.presentation.models.login.LoggedInUser
import com.kutluoglu.presentation.resource.Resource
import com.kutluoglu.presentation.resource.ResourceState
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-18.
 *
 */
open class LoginViewModel @Inject constructor (
        private val getUserUseCase: GetUserUseCase,
        private val isUserLoginUseCase: IsUserLoginUseCase,
        private val userMapper: UserMapper
) : ViewModel(), LoginContract {

    override fun login(userId: String) {
        _loginUserLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getUserUseCase.execute(LoginSubscriber(userMapper), userId)
    }

    override fun getLoginLiveData(): LiveData<Resource<LoggedInUser>> {
        return loginUserLiveData
    }

    override fun isUserLogin() {
        return isUserLoginUseCase.execute(IsUserLoginSubscriber())
    }

    override fun getIsUserLoginLiveData(): LiveData<Resource<Boolean>> {
        return isUserLoginLiveData
    }

    override fun loginSuccess() {
        _isUserLoginLiveData.postValue(Resource(ResourceState.SUCCESS, true, null))
    }

    override fun loginCompleted() {
        _loginUserLiveData.postValue(null)
        _isUserLoginLiveData.postValue(null)
    }

    override fun onCleared() {
        getUserUseCase.dispose()
        super.onCleared()
    }
}