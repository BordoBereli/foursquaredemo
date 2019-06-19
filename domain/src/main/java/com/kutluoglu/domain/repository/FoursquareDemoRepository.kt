package com.kutluoglu.domain.repository

import com.kutluoglu.domain.model.DomainVenue
import com.kutluoglu.domain.model.User
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by F.K. on 2019-06-16.
 *
 */

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */

interface FoursquareDemoRepository {
    fun getNearVenuesForSahibinden() : Flowable<List<DomainVenue>>
    fun getVenueByID(venueID : String) : Single<DomainVenue>
    fun getUser(userID: String?) : Single<User>
    fun removeUser() : Single<Boolean>
    fun isLogin() : Single<Boolean>
}