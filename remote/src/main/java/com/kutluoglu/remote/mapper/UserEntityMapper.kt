package com.kutluoglu.remote.mapper

import com.kutluoglu.data.model.DataUser
import com.kutluoglu.remote.model.user.UserResponse
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 *
 */

open class UserEntityMapper @Inject constructor(): EntityMapper<UserResponse, DataUser> {
    override fun mapFromRemote(type: UserResponse): DataUser {
        var dataUser = DataUser("0", "")
        if (type.meta.code == 200) {
            dataUser = dataUser.copy(type.response.user.id, """${type.response.user.firstName} ${type.response.user.lastName}""")
        }

        return dataUser
    }
}