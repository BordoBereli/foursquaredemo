package com.kutluoglu.domain.factory

import com.kutluoglu.domain.model.DomainVenue

/**
 * Created by F.K. on 2019-06-16.
 *
 */

/**
 * Factory class for data instances
 */

class DummyVenues {
    companion object Factory {
        fun getDummyVenues() : List<DomainVenue> {
            return listOf(
                    DomainVenue("5642aef9498e51025cf4a7a5", "Mr. Purple"),
                    DomainVenue("4bf58dd8d48988d1d5941735", "Mr. Yellow"),
                    DomainVenue("6bf58dd8d48988d1d5941453", "Mr. Green")
            )
        }
    }
}