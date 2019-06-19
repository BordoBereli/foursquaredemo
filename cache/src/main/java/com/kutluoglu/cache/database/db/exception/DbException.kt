package com.vibuy.legacy.cache.database.db.exception

/**
 * Created by F.K. on 2019-06-17.
 *
 */

class DbException : Exception() {
    override val message: String?
        get() = "There is an error on Foursquare Demo DB operation: " + super.message
}