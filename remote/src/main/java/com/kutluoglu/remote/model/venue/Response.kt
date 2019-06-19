package com.kutluoglu.remote.model.venue


import com.google.gson.annotations.SerializedName

data class Response(
        @SerializedName("venues")
        val venues: List<Venue>
)