package com.kutluoglu.remote.model.user


import com.google.gson.annotations.SerializedName

data class Lists(
        @SerializedName("count")
        val count: Int, // 2
        @SerializedName("groups")
        val groups: List<Group>
)