package com.kutluoglu.remote.model.user


import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("default")
        val default: Boolean, // true
        @SerializedName("prefix")
        val prefix: String, // https://fastly.4sqi.net/img/user/
        @SerializedName("suffix")
        val suffix: String // /blank_boy.png
)