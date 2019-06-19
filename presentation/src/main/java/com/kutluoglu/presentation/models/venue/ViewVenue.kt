package com.kutluoglu.presentation.models.venue

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by F.K. on 2019-06-17.
 *
 */

@Parcelize
data class ViewVenue (
        val id: String,
        val venueName: String
) : Parcelable