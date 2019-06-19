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

/**
 * Interface defining methods for the data operations related to FoursquareDemo.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */

interface DataStore {
    fun getUser(userID: String?) : Single<DataUser>

    fun saveVenues(venues: List<DataVenue>) : Completable
    fun getVenues() : Flowable<List<DataVenue>>
    fun getVenueByID(venueID: String) : Single<DataVenue>

    fun addUser(user: DataUser) : Completable
    fun removeUser() : Single<Boolean>

    fun isCached(): Single<Boolean>
    fun isLogin(): Single<Boolean>
}