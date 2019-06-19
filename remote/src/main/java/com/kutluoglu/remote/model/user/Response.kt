package com.kutluoglu.remote.model.user


import com.google.gson.annotations.SerializedName

data class Response(
        @SerializedName("user")
        val user: User
)