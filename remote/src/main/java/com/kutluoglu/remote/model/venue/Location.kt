package com.kutluoglu.remote.model.venue


import com.google.gson.annotations.SerializedName

data class Location(
        @SerializedName("address")
        val address: String, // İçerenköy Mah. Askent Sok No: 9 İçerenköy, Ataşehir
        @SerializedName("cc")
        val cc: String, // TR
        @SerializedName("city")
        val city: String, // İstanbul
        @SerializedName("country")
        val country: String, // Türkiye
        @SerializedName("distance")
        val distance: Int, // 99
        @SerializedName("formattedAddress")
        val formattedAddress: List<String>,
        @SerializedName("labeledLatLngs")
        val labeledLatLngs: List<LabeledLatLng>,
        @SerializedName("lat")
        val lat: Double, // 40.961308041071526
        @SerializedName("lng")
        val lng: Double, // 29.11177851808641
        @SerializedName("postalCode")
        val postalCode: String, // 81120
        @SerializedName("state")
        val state: String // İstanbul
)