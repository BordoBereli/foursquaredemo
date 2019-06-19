package com.kutluoglu.domain.factory

import com.kutluoglu.domain.model.User

/**
 * Created by F.K. on 2019-06-16.
 *
 */

/**
 * Factory class for User related instances
 */

class UserFactory {
    companion object Factory {
        fun makeUser() : User {
            return User("1453", "Sahibinden")
        }

        fun isLogin() : Boolean {
            return true
        }
        fun removeUser() {}
    }
}