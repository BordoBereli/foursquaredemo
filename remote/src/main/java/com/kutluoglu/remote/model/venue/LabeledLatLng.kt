package com.kutluoglu.remote.model.venue


import com.google.gson.annotations.SerializedName

data class LabeledLatLng(
        @SerializedName("label")
        val label: String, // display
        @SerializedName("lat")
        val lat: Double, // 40.961308041071526
        @SerializedName("lng")
        val lng: Double // 29.11177851808641
)