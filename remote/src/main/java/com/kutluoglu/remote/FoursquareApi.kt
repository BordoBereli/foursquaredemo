package com.kutluoglu.remote

import com.kutluoglu.remote.model.user.UserResponse
import com.kutluoglu.remote.model.venue.VenueResponse
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by F.K. on 2019-06-17.
 *
 */

/**
 * Defines the abstract methods used for interacting with the Foursquare API
 */

interface FoursquareApi {
    /**
     * Get Venues with Intent browse
     */
    @GET("venues/search")
    fun getVenues(@Query("client_id") clientId: String,
                  @Query("client_secret") clientSecret: String,
                  @Query("v") v: String,
                  @Query("ll") ll: String,
                  @Query("intent") intent: String,
                  @Query("radius") radius: Int,
                  @Query("limit") limit: Int) : Flowable<VenueResponse>

    /**
     * Get User with its Id
     */
    @GET("users/{userID}")
    fun getUser(@Path("userID") userID: String?,
                @Query("client_id") clientId: String,
                @Query("client_secret") clientSecret: String,
                @Query("v") v: String): Single<UserResponse>
}