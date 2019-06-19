package com.kutluoglu.remote.model.venue


import com.google.gson.annotations.SerializedName

data class Meta(
        @SerializedName("code")
        val code: Int, // 200
        @SerializedName("requestId")
        val requestId: String // 5d062cd4351e3d1fff4780f8
)