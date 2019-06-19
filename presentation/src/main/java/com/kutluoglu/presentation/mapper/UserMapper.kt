package com.kutluoglu.presentation.mapper

import com.kutluoglu.domain.model.User
import com.kutluoglu.presentation.models.login.LoggedInUser
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-18.
 *
 */
open class UserMapper @Inject constructor(): ViewMapper<User, LoggedInUser> {
    override fun mapToView(type: User): LoggedInUser {
        return LoggedInUser(type.userID, type.userName)
    }

    override fun mapFromView(type: LoggedInUser): User {
        return User(type.userId, type.displayName)
    }
}