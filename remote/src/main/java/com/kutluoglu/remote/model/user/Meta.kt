package com.kutluoglu.remote.model.user


import com.google.gson.annotations.SerializedName

data class Meta(
        @SerializedName("code")
        val code: Int, // 200
        @SerializedName("errorType")
        val errorType: String?, //"param_error"
        @SerializedName("errorDetail")
        val errorDetail: String?, //"Must provide a valid user ID or 'self.'"
        @SerializedName("requestId")
        val requestId: String // 5d0637a8342adf0030b6f309
)