package com.entity.picsumgallery.data.remote

import com.entity.picsumgallery.data.remote.dto.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

    @GET("list")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit : Int
    ): ImageResponse
}