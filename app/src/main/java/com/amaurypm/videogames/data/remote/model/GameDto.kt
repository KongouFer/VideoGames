package com.amaurypm.videogames.data.remote.model

import com.google.gson.annotations.SerializedName

data class GameDto(
    @SerializedName("id")
    var id: String,
    @SerializedName("thumbnail")
    var image: String,
    @SerializedName("title")
    var title: String
)
