package com.kutluoglu.remote.model.user


import com.google.gson.annotations.SerializedName

data class Mayorships(
        @SerializedName("count")
        val count: Int, // 0
        @SerializedName("items")
        val items: List<Any>
)