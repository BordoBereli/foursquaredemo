package com.kutluoglu.cache.mapper

import com.kutluoglu.cache.database.entity.UserEntity
import com.kutluoglu.data.model.DataUser
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 *
 */

/**
 * Map a [UserEntity] instance to and from a [DataUser] instance when data is moving between
 * this layer and the Data layer
 */

open class DbUserMapper @Inject constructor() : DbMapper<DataUser, UserEntity> {
    override fun mapToCached(type: DataUser): UserEntity {
        return UserEntity(type.userID, type.userName)
    }

    override fun mapFromCached(type: UserEntity): DataUser {
        return DataUser(type.id, type.fullName)
    }
}