package com.kutluoglu.domain.useCases

import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.factory.VenueFactory
import com.kutluoglu.domain.interactor.GetNearVenuesForSahibindenUseCase
import com.kutluoglu.domain.model.DomainVenue
import com.kutluoglu.domain.repository.FoursquareDemoRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

/**
 * Created by F.K. on 2019-06-16.
 *
 */

class GetNearVenuesForSahibindenUseCaseTest {
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockRepository: FoursquareDemoRepository

    private lateinit var getNearrVenuesForSahibindenUseCase: GetNearVenuesForSahibindenUseCase

    @Before
    fun setup() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockRepository = mock()

        getNearrVenuesForSahibindenUseCase = GetNearVenuesForSahibindenUseCase(
                mockRepository, mockThreadExecutor, mockPostExecutionThread
        )
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getNearrVenuesForSahibindenUseCase.buildUseCaseFlowable()
        verify(mockRepository).getNearVenuesForSahibinden()
    }

    @Test
    fun buildCaseObservablesCompletes() {
        stubFoursquareRepositoryGetNearVenuesForSahibinden(
                Flowable.just(VenueFactory.makeVenueList())
        )

        val testObserver = getNearrVenuesForSahibindenUseCase.buildUseCaseFlowable().test()
        testObserver.assertComplete()

    }

    @Test
    fun buildCaseObservableReturnData() {
        val listVenues = VenueFactory.makeVenueList()
        stubFoursquareRepositoryGetNearVenuesForSahibinden(
                Flowable.just(listVenues)
        )

        val testObserver = getNearrVenuesForSahibindenUseCase.buildUseCaseFlowable().test()
        testObserver.assertValue(listVenues)
    }

    private fun stubFoursquareRepositoryGetNearVenuesForSahibinden(flowable: Flowable<List<DomainVenue>>) {
        whenever(mockRepository.getNearVenuesForSahibinden()).thenReturn(flowable)
    }

}