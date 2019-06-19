package com.kutluoglu.remote.mapper

import com.kutluoglu.data.model.DataVenue
import com.kutluoglu.remote.model.venue.VenueResponse
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 *
 */

open class VenueEntityMapper @Inject constructor(): EntityMapper<VenueResponse, List<DataVenue>> {
    override fun mapFromRemote(type: VenueResponse): List<DataVenue> {
        val dataVenues = mutableListOf<DataVenue>()
        if (type.meta.code == 200) {
            type.response.venues.map {
                dataVenues.add(DataVenue(it.id, it.name))
            }
        }

        return dataVenues
    }
}