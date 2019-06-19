package com.kutluoglu.presentation.viewModels.getVenues

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kutluoglu.domain.model.DomainVenue
import com.kutluoglu.presentation.mapper.PresentationMapper
import com.kutluoglu.presentation.models.venue.ViewVenue
import com.kutluoglu.presentation.resource.Resource
import com.kutluoglu.presentation.resource.ResourceState
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by F.K. on 2019-06-17.
 *
 */

val venueLiveData: MutableLiveData<Resource<List<ViewVenue>>> = MutableLiveData()

class VenueSubscriber (
        private val viewMapper: PresentationMapper
) : DisposableSubscriber<List<DomainVenue>>() {

    override fun onNext(domainVenues: List<DomainVenue>?) {
        if (domainVenues != null && domainVenues.isNotEmpty())
            handleSuccessCase(viewMapper.mapToView(domainVenues))
        else handleErrorCase("The venue list is empty")
    }

    override fun onError(error: Throwable?) = handleErrorCase(error?.message)

    override fun onComplete() = handleCompleteCase()

    private fun handleSuccessCase(venueList: List<ViewVenue>) {
        venueLiveData.postValue(Resource(ResourceState.SUCCESS, venueList, null))
    }

    private fun handleErrorCase(message: String?) {
        venueLiveData.postValue(Resource(ResourceState.ERROR, null, message))
    }

    private fun handleCompleteCase() {
        Log.e("VenueSubscriber", "VenueSubscriber is completed!!!")
    }

}