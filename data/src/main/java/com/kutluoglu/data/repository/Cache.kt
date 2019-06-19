package com.kutluoglu.data.repository

import com.kutluoglu.data.model.DataUser
import com.kutluoglu.data.model.DataVenue
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by F.K. on 2019-06-17.
 *
 */

interface Cache {
    /**
     * Save list of [DataVenue] to the cache
     */
    fun saveVenues(venues: List<DataVenue>) : Completable

    /**
     * Retrieve a [List] of [DataVenue] from the cache
     */
    fun getVenues() : Flowable<List<DataVenue>>

    /**
     * Retrieve a [DataVenue] from the cache
     */
    fun getVenueByID(venueID: String) : Single<DataVenue>

    /**
     * Add a [DataUser] for Login Info to cache it
     */
    fun addUser(user: DataUser) : Completable

    /**
     * Retrieve a [DataUser] from cache
     */
    fun getUser(userID: String?) : Single<DataUser>

    /**
     * Remove all [DataUser] from cache
     */
    fun removeUser() : Single<Boolean>

    /**
     * Check whether there is a list of Venues stored in the cache.
     *
     * @return true if the list is cached, otherwise false
     */
    fun isCached(): Single<Boolean>

    /**
     * Check whether there is a user stored in the cache.
     *
     * @return true if the user is cached, otherwise false
     */
    fun isLogin(): Single<Boolean>

    /**
     * Set a point in time at when the cache was last updated.
     */
    fun setLastCacheTime()

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time.
     */
    fun isExpired() : Boolean
}