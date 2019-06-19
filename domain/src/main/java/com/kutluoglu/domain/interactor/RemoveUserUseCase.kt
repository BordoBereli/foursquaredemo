package com.kutluoglu.domain.interactor

import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.SingleUseCase
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.repository.FoursquareDemoRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-16.
 */

open class RemoveUserUseCase @Inject constructor(
        private val repository: FoursquareDemoRepository,
        threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
) : SingleUseCase<Boolean, Void>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseSingle(params: Void?): Single<Boolean> {
        return repository.removeUser()
    }
}
