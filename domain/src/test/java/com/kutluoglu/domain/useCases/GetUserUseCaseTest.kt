package com.kutluoglu.domain.useCases

import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.factory.UserFactory
import com.kutluoglu.domain.interactor.GetUserUseCase
import com.kutluoglu.domain.model.User
import com.kutluoglu.domain.repository.FoursquareDemoRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by F.K. on 2019-06-16.
 *
 */

class GetUserUseCaseTest {
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockRepository: FoursquareDemoRepository

    private lateinit var getUserUseCase: GetUserUseCase
    private val userID = "1453"

    @Before
    fun setup() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockRepository = mock()

        getUserUseCase = GetUserUseCase(
                mockRepository, mockThreadExecutor, mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getUserUseCase.buildUseCaseSingle(userID)
        verify(mockRepository).getUser(userID)
    }


    @Test
    fun buildCaseObservablesCompletes() {
        stubFoursquareDemoRepositoryGetUser(Single.just(UserFactory.makeUser()))

        val testObserver = getUserUseCase.buildUseCaseSingle(userID).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnData() {
        val user = UserFactory.makeUser()
        stubFoursquareDemoRepositoryGetUser(Single.just(user))

        val testObserver = getUserUseCase.buildUseCaseSingle(userID).test()
        testObserver.assertValue(user)
    }


    fun stubFoursquareDemoRepositoryGetUser(single : Single<User>) {
        whenever(mockRepository.getUser(userID)).thenReturn(single)
    }
}