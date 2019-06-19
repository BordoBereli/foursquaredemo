package com.kutluoglu.remote.model.user


import com.google.gson.annotations.SerializedName

data class Item(
        @SerializedName("public")
        val `public`: Boolean, // true
        @SerializedName("canonicalUrl")
        val canonicalUrl: String, // https://foursquare.com/user/543332126/list/venuelikes
        @SerializedName("collaborative")
        val collaborative: Boolean, // false
        @SerializedName("description")
        val description: String,
        @SerializedName("editable")
        val editable: Boolean, // false
        @SerializedName("id")
        val id: String, // 543332126/venuelikes
        @SerializedName("listItems")
        val listItems: ListItems,
        @SerializedName("name")
        val name: String, // Fahri’s Liked Places
        @SerializedName("type")
        val type: String, // likes
        @SerializedName("url")
        val url: String // /user/543332126/list/venuelikes
)