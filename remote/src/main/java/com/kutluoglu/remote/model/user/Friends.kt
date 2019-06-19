package com.kutluoglu.remote.model.user


import com.google.gson.annotations.SerializedName

data class Friends(
        @SerializedName("count")
        val count: Int, // 0
        @SerializedName("groups")
        val groups: List<GroupX>
)