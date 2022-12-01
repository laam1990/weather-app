package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ErrorBodyData(
    @SerializedName("error")
    val error: ErrorInfoData? = null
)

data class ErrorInfoData(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("message")
    val message: String? = null,
)
