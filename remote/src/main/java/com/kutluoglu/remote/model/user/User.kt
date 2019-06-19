package com.kutluoglu.remote.model.user


import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("bio")
        val bio: String,
        @SerializedName("canonicalUrl")
        val canonicalUrl: String, // https://foursquare.com/user/543332126
        @SerializedName("checkins")
        val checkins: Checkins,
        @SerializedName("contact")
        val contact: Contact,
        @SerializedName("firstName")
        val firstName: String, // Fahri
        @SerializedName("friends")
        val friends: Friends,
        @SerializedName("gender")
        val gender: String, // none
        @SerializedName("homeCity")
        val homeCity: String, // Istanbul, Istanbul
        @SerializedName("id")
        val id: String, // 543332126
        @SerializedName("lastName")
        val lastName: String, // K
        @SerializedName("lenses")
        val lenses: List<Any>,
        @SerializedName("lists")
        val lists: Lists,
        @SerializedName("mayorships")
        val mayorships: Mayorships,
        @SerializedName("photo")
        val photo: Photo,
        @SerializedName("photos")
        val photos: Photos,
        @SerializedName("tips")
        val tips: Tips,
        @SerializedName("type")
        val type: String // user
)