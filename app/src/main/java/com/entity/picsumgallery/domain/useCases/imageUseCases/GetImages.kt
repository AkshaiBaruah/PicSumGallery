package com.entity.picsumgallery.domain.useCases.imageUseCases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.entity.picsumgallery.domain.model.ImageItem
import com.entity.picsumgallery.domain.repository.ImageRepository
import javax.inject.Inject

class GetImages @Inject constructor(
    private val imageRepository: ImageRepository
) {
    operator fun invoke() : LiveData<PagingData<ImageItem>> {
        return imageRepository.getImages()
    }
}