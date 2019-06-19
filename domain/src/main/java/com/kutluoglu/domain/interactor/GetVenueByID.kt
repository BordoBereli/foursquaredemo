package com.kutluoglu.domain.interactor

import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.SingleUseCase
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.model.DomainVenue
import com.kutluoglu.domain.repository.FoursquareDemoRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetVenueByID @Inject constructor(
        private val repository: FoursquareDemoRepository,
        threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
): SingleUseCase<DomainVenue, String>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseSingle(params: String?): Single<DomainVenue> {
        return if (params != null && params.isNotBlank()) {
            repository.getVenueByID(params)
        } else {
            Single.error(Throwable("Venue ID must not be null or empty"))
        }
    }

}
