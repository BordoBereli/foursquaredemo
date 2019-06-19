package com.kutluoglu.remote.model.venue


import com.google.gson.annotations.SerializedName

data class Venue(
        @SerializedName("categories")
        val categories: List<Category>,
        @SerializedName("hasPerk")
        val hasPerk: Boolean, // false
        @SerializedName("id")
        val id: String, // 511a6ac8759629b50618c351
        @SerializedName("location")
        val location: Location,
        @SerializedName("name")
        val name: String, // Kosifler Oto
        @SerializedName("referralId")
        val referralId: String, // v-1560685780
        @SerializedName("venuePage")
        val venuePage: VenuePage
)