package com.kutluoglu.remote.model.venue


import com.google.gson.annotations.SerializedName

data class VenueResponse(
        @SerializedName("meta")
        val meta: Meta,
        @SerializedName("response")
        val response: Response
)