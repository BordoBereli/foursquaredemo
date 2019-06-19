package com.kutluoglu.data.source

import com.kutluoglu.data.model.DataUser
import com.kutluoglu.data.model.DataVenue
import com.kutluoglu.data.repository.DataStore
import com.kutluoglu.data.repository.Remote
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
 * with the remote data source
 */

open class RemoteDataStore @Inject constructor(
        private val remote: Remote
) : DataStore {
    override fun getUser(userID: String?): Single<DataUser> {
        return remote.getUser(userID)
    }

    override fun getVenues(): Flowable<List<DataVenue>> {
        return remote.getVenues()
    }

    override fun getVenueByID(venueID: String): Single<DataVenue> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support getVenueByID() operation!!!")
    }

    override fun saveVenues(venues: List<DataVenue>): Completable {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support saveVenues() operation!!!")
    }

    override fun addUser(user: DataUser): Completable {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support addUser() operation!!!")
    }

    override fun removeUser(): Single<Boolean> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support removeUser() operation!!!")
    }

    override fun isCached() : Single<Boolean> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support isCached() operation!!!")
    }

    override fun isLogin(): Single<Boolean> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support isLogin() operation!!!")
    }
}