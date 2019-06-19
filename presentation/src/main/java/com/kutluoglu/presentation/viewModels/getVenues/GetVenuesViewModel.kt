package com.kutluoglu.presentation.viewModels.getVenues

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kutluoglu.domain.interactor.GetNearVenuesForSahibindenUseCase
import com.kutluoglu.presentation.mapper.PresentationMapper
import com.kutluoglu.presentation.models.venue.ViewVenue
import com.kutluoglu.presentation.resource.Resource
import com.kutluoglu.presentation.resource.ResourceState
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 */
class GetVenuesViewModel @Inject constructor(
        private val getVenueUseCase: GetNearVenuesForSahibindenUseCase,
        private val mapper: PresentationMapper
) : ViewModel(), VenuesContract {

    override fun getVenueLiveData(): LiveData<Resource<List<ViewVenue>>> {
        return venueLiveData
    }

    override fun showVenues() {
        venueLiveData.postValue(Resource(ResourceState.LOADING, null,  null))
        return getVenueUseCase.execute(VenueSubscriber(mapper))
    }

    override fun onCleared() {
        super.onCleared()
        getVenueUseCase.dispose()
    }
}