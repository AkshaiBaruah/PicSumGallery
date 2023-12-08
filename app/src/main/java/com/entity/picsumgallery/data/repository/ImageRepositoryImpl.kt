package com.entity.picsumgallery.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.entity.picsumgallery.data.remote.ImageApi
import com.entity.picsumgallery.data.remote.ImagePagingSource
import com.entity.picsumgallery.domain.model.ImageItem
import com.entity.picsumgallery.domain.repository.ImageRepository
import com.entity.picsumgallery.util.Constants
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageApi: ImageApi,
) : ImageRepository {

    override fun getImages(): LiveData<PagingData<ImageItem>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE),
            pagingSourceFactory = {
                ImagePagingSource(
                    imageApi = imageApi
                )
            }
        ).liveData
    }
}