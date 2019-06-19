package com.kutluoglu.domain.useCases

import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.factory.UserFactory
import com.kutluoglu.domain.interactor.IsUserLoginUseCase
import com.kutluoglu.domain.repository.FoursquareDemoRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by F.K. on 2019-06-18.
 *
 */

class IsUserLoginUseCaseTest {
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockRepository: FoursquareDemoRepository

    private lateinit var isUserLoginUseCase: IsUserLoginUseCase

    @Before
    fun setup() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockRepository = mock()

        isUserLoginUseCase = IsUserLoginUseCase(
                mockRepository, mockThreadExecutor, mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        isUserLoginUseCase.buildUseCaseSingle()
        verify(mockRepository).isLogin()
    }


    @Test
    fun buildCaseObservablesCompletes() {
        stubFoursquareDemoRepositoryIsLogin(Single.just(UserFactory.isLogin()))

        val testObserver = isUserLoginUseCase.buildUseCaseSingle().test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnData() {
        val isLogin = UserFactory.isLogin()
        stubFoursquareDemoRepositoryIsLogin(Single.just(isLogin))

        val testObserver = isUserLoginUseCase.buildUseCaseSingle().test()
        testObserver.assertValue(isLogin)
    }


    fun stubFoursquareDemoRepositoryIsLogin(single : Single<Boolean>) {
        whenever(mockRepository.isLogin()).thenReturn(single)
    }
}