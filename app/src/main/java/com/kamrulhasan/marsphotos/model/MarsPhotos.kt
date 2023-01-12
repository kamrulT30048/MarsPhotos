package com.kamrulhasan.marsphotos.model

import com.squareup.moshi.Json

data class MarsPhotos(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String
)
