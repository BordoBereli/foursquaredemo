package com.kutluoglu.data.source

import com.kutluoglu.data.model.DataUser
import com.kutluoglu.data.model.DataVenue
import com.kutluoglu.data.repository.Cache
import com.kutluoglu.data.repository.DataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 *
 */

/**
 * Implementation of the [DataStore] interface to provide a means of communicating
 * with the cache data source
 */
open class CacheDataStore @Inject constructor(
        private val cache: Cache
) : DataStore {
    override fun saveVenues(venues: List<DataVenue>): Completable {
        return cache.saveVenues(venues)
    }

    override fun getVenues(): Flowable<List<DataVenue>> {
        return cache.getVenues()
    }

    override fun getVenueByID(venueID: String): Single<DataVenue> {
        return cache.getVenueByID(venueID)
    }

    override fun addUser(user: DataUser): Completable {
        return cache.addUser(user)
    }

    override fun getUser(userID: String?): Single<DataUser> {
        return cache.getUser(userID )
    }

    override fun removeUser(): Single<Boolean> {
        return cache.removeUser()
    }

    override fun isCached(): Single<Boolean> {
        return cache.isCached()
    }

    override fun isLogin(): Single<Boolean> {
        return cache.isLogin()
    }

}