package com.kutluoglu.domain.useCases

import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.factory.VenueFactory
import com.kutluoglu.domain.interactor.GetVenueByID
import com.kutluoglu.domain.model.DomainVenue
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
class GetVenueByIDTest {
    private lateinit var mockRepository: FoursquareDemoRepository
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread

    private lateinit var getVenueByID: GetVenueByID
    private val venueID = "5642aef9498e51025cf4a7a5"

    @Before
    fun setup() {
        mockRepository = mock()
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()

        getVenueByID = GetVenueByID(
                mockRepository, mockThreadExecutor, mockPostExecutionThread
        )
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getVenueByID.buildUseCaseSingle(venueID)
        verify(mockRepository).getVenueByID(venueID)
    }

    @Test
    fun buildUseCaseObservableComplete() {
        stubFoursquareDemoRepositoryGetVenueByID(
                Single.just(VenueFactory.makeVenueList()[0]), venueID
        )

        val testObserver = getVenueByID.buildUseCaseSingle(venueID).test()
        testObserver.assertComplete()

    }

    @Test
    fun buildUseCaseObservableReturnData() {
        val demoVenue = VenueFactory.makeVenueList()[0]
        stubFoursquareDemoRepositoryGetVenueByID(
                Single.just(demoVenue), venueID
        )

        val testObserver = getVenueByID.buildUseCaseSingle(venueID).test()
        testObserver.assertValue(demoVenue)
    }

    @Test
    fun buildUseCaseObservableThrowError() {
        stubFoursquareDemoRepositoryGetVenueByID(
                Single.error(Throwable("Venue ID must not be null or empty")), ""
        )

        val testObserver = getVenueByID.buildUseCaseSingle("").test()
        testObserver.assertFailureAndMessage(
                Throwable::class.java, "Venue ID must not be null or empty")
    }

    private fun stubFoursquareDemoRepositoryGetVenueByID(single: Single<DomainVenue>, venueId: String) {
        whenever(mockRepository.getVenueByID(venueId)).thenReturn(single)
    }

}