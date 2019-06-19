package com.kutluoglu.domain.interactor

import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.SingleUseCase
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.model.User
import com.kutluoglu.domain.repository.FoursquareDemoRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-16.
 */

open class GetUserUseCase @Inject constructor(
        private val repository: FoursquareDemoRepository,
        threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
) : SingleUseCase<User, String>(threadExecutor, postExecutionThread) {
   public override fun buildUseCaseSingle(params: String?): Single<User> {
        return repository.getUser(params)
    }
}