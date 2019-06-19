package com.kutluoglu.remote.model.venue


import com.google.gson.annotations.SerializedName

data class Icon(
        @SerializedName("prefix")
        val prefix: String, // https://ss3.4sqi.net/img/categories_v2/shops/automotive_
        @SerializedName("suffix")
        val suffix: String // .png
)