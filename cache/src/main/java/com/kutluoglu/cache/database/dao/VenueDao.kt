package com.kutluoglu.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kutluoglu.cache.database.db.constant.DbConstants
import com.kutluoglu.cache.database.entity.VenueEntity
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by F.K. on 2019-06-17.
 *
 */

@Dao
interface VenueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllVenues(list: List<VenueEntity>)

    @Query(DbConstants.QUERY_GET_VENUES)
    fun getAllVenues() : Flowable<List<VenueEntity>>

    @Query(DbConstants.QUERY_GET_CATALOGS_BY_ID)
    fun getVenueByID(venueId: String) : Single<VenueEntity>
}