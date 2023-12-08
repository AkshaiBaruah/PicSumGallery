package com.entity.picsumgallery.domain.useCases.imageUseCases

import javax.inject.Inject

data class ImageUseCases @Inject constructor(
    val getImages: GetImages
)
