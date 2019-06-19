package com.kutluoglu.remote.model.venue


import com.google.gson.annotations.SerializedName

data class Category(
        @SerializedName("icon")
        val icon: Icon,
        @SerializedName("id")
        val id: String, // 4eb1c1623b7b52c0e1adc2ec
        @SerializedName("name")
        val name: String, // Auto Dealership
        @SerializedName("pluralName")
        val pluralName: String, // Auto Dealerships
        @SerializedName("primary")
        val primary: Boolean, // true
        @SerializedName("shortName")
        val shortName: String // Auto Dealer
)