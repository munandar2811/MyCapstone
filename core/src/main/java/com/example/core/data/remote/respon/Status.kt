package com.example.core.data.remote.respon

import com.google.gson.annotations.SerializedName

data class Status(

    @field:SerializedName("error_message")
    val errorMessage: Any? = null,

    @field:SerializedName("elapsed")
    val elapsed: Int? = null,

    @field:SerializedName("credit_count")
    val creditCount: Int? = null,

    @field:SerializedName("error_code")
    val errorCode: Int? = null,

    @field:SerializedName("timestamp")
    val timestamp: String? = null,

    @field:SerializedName("notice")
    val notice: Any? = null
)

