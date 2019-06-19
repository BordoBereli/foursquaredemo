package com.kutluoglu.data.mapper

import com.kutluoglu.data.model.DataVenue
import com.kutluoglu.domain.model.DomainVenue
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 *
 */

/**
 * Map a [DataVenue] to and from a [DomainVenue] instance when data is moving between
 * this layer and the Domain layer
 */

class VenueMapper @Inject constructor() : Mapper<DataVenue, DomainVenue> {
    override fun mapFromEntityToDomainModel(type: DataVenue): DomainVenue {
        return DomainVenue(type.id, type.name)
    }

    override fun mapToEntityFromDomainModel(type: DomainVenue): DataVenue {
        return DataVenue(type.id, type.name)
    }
}