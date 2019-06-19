package com.kutluoglu.cache.mapper

import com.kutluoglu.cache.database.entity.VenueEntity
import com.kutluoglu.data.model.DataVenue
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 *
 */

/**
 * Map a [VenueEntity] instance to and from a [DataVenue] instance when data is moving between
 * this layer and the Data layer
 */

open class DbVenueMapper @Inject constructor() : DbMapper<DataVenue, VenueEntity> {
    override fun mapToCached(type: DataVenue): VenueEntity {
        return VenueEntity(type.id, type.name)
    }

    override fun mapFromCached(type: VenueEntity): DataVenue {
        return DataVenue(type.id, type.venueName)
    }

}