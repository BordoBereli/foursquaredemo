package com.kutluoglu.remote

import com.kutluoglu.data.model.DataUser
import com.kutluoglu.data.model.DataVenue
import com.kutluoglu.data.repository.Remote
import com.kutluoglu.remote.constant.ApiConstants
import com.kutluoglu.remote.mapper.UserEntityMapper
import com.kutluoglu.remote.mapper.VenueEntityMapper
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 *
 */

/**
 * Remote implementation for retrieving Foursquare Api instances. This class implements the
 * [Remote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */

class FoursquareRemoteImp @Inject constructor(
        private val foursquareApi: FoursquareApi,
        private val venueEntityMapper: VenueEntityMapper,
        private val userEntityMapper: UserEntityMapper
) : Remote {
    override fun getVenues(): Flowable<List<DataVenue>> {
        return foursquareApi.getVenues(ApiConstants.CLIENT_ID, ApiConstants.CLIENT_SECRET,
                ApiConstants.V, ApiConstants.LL, ApiConstants.INTENT, ApiConstants.RADIUS,
                ApiConstants.LIMIT)
                .map {
                   venueEntityMapper.mapFromRemote(it)
                }

    }

    override fun getUser(userID: String?): Single<DataUser> {
        return foursquareApi.getUser(userID,
                ApiConstants.CLIENT_ID, ApiConstants.CLIENT_SECRET, ApiConstants.V)
                .map {
                    userEntityMapper.mapFromRemote(it)
                }
    }
}