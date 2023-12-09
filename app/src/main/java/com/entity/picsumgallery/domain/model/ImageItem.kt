package com.entity.picsumgallery.domain.model

data class ImageItem(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)