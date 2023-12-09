package com.entity.picsumgallery.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.entity.picsumgallery.domain.model.ImageItem
import javax.inject.Inject

class ImagePagingSource @Inject constructor(
    private val imageApi : ImageApi
) : PagingSource<Int, ImageItem>() {

    override fun getRefreshKey(
        state: PagingState<Int, ImageItem>
    ): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, ImageItem> {

        return try{
            val page = params.key ?: 1
            val limit = params.loadSize
            val imageResponse = imageApi.getImages(page = page , limit = limit)

            LoadResult.Page(
                data = imageResponse,
                nextKey = page + 1,
                prevKey = if(page == 1) null else page - 1
            )
        }
        catch (e : Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

}