package com.kutluoglu.remote.model.user


import com.google.gson.annotations.SerializedName

data class GroupX(
        @SerializedName("count")
        val count: Int, // 0
        @SerializedName("items")
        val items: List<Any>,
        @SerializedName("name")
        val name: String, // Other friends
        @SerializedName("type")
        val type: String // others
)