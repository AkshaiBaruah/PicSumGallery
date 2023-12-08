package com.entity.picsumgallery.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.entity.picsumgallery.domain.model.ImageItem

interface ImageRepository {
    fun getImages() : LiveData<PagingData<ImageItem>>
}