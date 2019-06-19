package com.kutluoglu.remote.model.user


import com.google.gson.annotations.SerializedName

data class UserResponse(
        @SerializedName("meta")
        val meta: Meta,
        @SerializedName("notifications")
        val notifications: List<Any>,
        @SerializedName("response")
        val response: Response
)