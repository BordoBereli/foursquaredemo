package com.kutluoglu.data.mapper

import com.kutluoglu.data.model.DataUser
import com.kutluoglu.domain.model.User
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 *
 */

class UserMapper @Inject constructor() : Mapper<DataUser, User> {
    override fun mapFromEntityToDomainModel(type: DataUser): User {
        return User(type.userID, type.userName)
    }

    override fun mapToEntityFromDomainModel(type: User): DataUser {
        return DataUser(type.userID, type.userName)
    }
}