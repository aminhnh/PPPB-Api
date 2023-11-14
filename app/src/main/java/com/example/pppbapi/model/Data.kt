package com.example.pppbapi.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("name")
    val name : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("type")
    val type : String,
    @SerializedName("role")
    val role : String,
    @SerializedName("image")
    val image : String,
    @SerializedName("twitter")
    val twitter : String,
    @SerializedName("github")
    val github : String,
    @SerializedName("website")
    val website : String,
)
