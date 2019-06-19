package com.kutluoglu.presentation.viewModels.getVenues

import androidx.lifecycle.LiveData
import com.kutluoglu.presentation.models.venue.ViewVenue
import com.kutluoglu.presentation.resource.Resource

/**
 * Created by F.K. on 2019-06-17.
 *
 */

interface VenuesContract {
    fun getVenueLiveData() : LiveData<Resource<List<ViewVenue>>>
    fun showVenues()
}