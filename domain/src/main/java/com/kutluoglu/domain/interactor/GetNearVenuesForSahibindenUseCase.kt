package com.kutluoglu.domain.interactor

import com.kutluoglu.domain.executor.FlowableUseCase
import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.model.DomainVenue
import com.kutluoglu.domain.repository.FoursquareDemoRepository
import io.reactivex.Flowable
import javax.inject.Inject

open class GetNearVenuesForSahibindenUseCase @Inject constructor(
        private val repository: FoursquareDemoRepository,
        threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
) : FlowableUseCase<List<DomainVenue>, Void>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseFlowable(params: Void?): Flowable<List<DomainVenue>> {
        return repository.getNearVenuesForSahibinden()
    }

}
