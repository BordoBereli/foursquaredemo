package com.kutluoglu.domain.useCases

import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.interactor.RemoveUserUseCase
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

class RemoveUserUseCaseTest {
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockRepository: FoursquareDemoRepository

    private lateinit var removeUserUseCase: RemoveUserUseCase

    @Before
    fun setup() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockRepository = mock()

        removeUserUseCase = RemoveUserUseCase(
                mockRepository, mockThreadExecutor, mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        removeUserUseCase.buildUseCaseSingle()
        verify(mockRepository).removeUser()
    }


    @Test
    fun buildCaseObservablesCompletes() {
        stubFoursquareDemoRepositoryRemoveUser(Single.just(true))

        val testObserver = removeUserUseCase.buildUseCaseSingle().test()
        testObserver.assertComplete()
    }


    fun stubFoursquareDemoRepositoryRemoveUser(single : Single<Boolean>) {
        whenever(mockRepository.removeUser()).thenReturn(single)
    }
}