package com.kutluoglu.remote.model.user


import com.google.gson.annotations.SerializedName

data class Group(
        @SerializedName("count")
        val count: Int, // 2
        @SerializedName("items")
        val items: List<Item>,
        @SerializedName("type")
        val type: String // yours
)