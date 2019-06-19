package com.kutluoglu.data.repository

import com.kutluoglu.data.model.DataUser
import com.kutluoglu.data.model.DataVenue
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by F.K. on 2019-06-17.
 *
 */
interface Remote {
    /**
     * Retrieve a [List] of [DataVenue] from the remote API Service
     */
    fun getVenues() : Flowable<List<DataVenue>>

    /**
     * Retrieve a [DataUser] from the remote API Service
     */
    fun getUser(userID: String?) : Single<DataUser>
}