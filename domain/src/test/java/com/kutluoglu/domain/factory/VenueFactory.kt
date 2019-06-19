package com.kutluoglu.domain.factory

import com.kutluoglu.domain.model.DomainVenue

/**
 * Created by F.K. on 2019-06-16.
 *
 */

/**
 * Factory class for DomainVenue related instances
 */

class VenueFactory {
    companion object Factory {
        fun makeVenueList() : List<DomainVenue> {
            return DummyVenues.getDummyVenues()
        }
    }
}