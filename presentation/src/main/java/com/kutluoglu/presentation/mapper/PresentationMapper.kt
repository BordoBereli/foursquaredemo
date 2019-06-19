package com.kutluoglu.presentation.mapper

import com.kutluoglu.domain.model.DomainVenue
import com.kutluoglu.presentation.models.venue.ViewVenue
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 *
 */

open class PresentationMapper @Inject constructor() : ViewMapper<List<DomainVenue>, List<ViewVenue>> {
    override fun mapToView(typeList: List<DomainVenue>): List<ViewVenue> {
        val viewList = mutableListOf<ViewVenue>()
        typeList.map { type ->
            viewList.add(ViewVenue(type.id, type.name))
        }

        return viewList
    }

    override fun mapFromView(typeList: List<ViewVenue>): List<DomainVenue> {
        val domainList = mutableListOf<DomainVenue>()
        typeList.map { type ->
            domainList.add(DomainVenue(type.id, type.venueName))
        }

        return domainList
    }

}