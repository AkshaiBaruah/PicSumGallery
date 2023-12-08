package com.entity.picsumgallery.di

import com.entity.picsumgallery.data.remote.ImageApi
import com.entity.picsumgallery.data.repository.ImageRepositoryImpl
import com.entity.picsumgallery.domain.repository.ImageRepository
import com.entity.picsumgallery.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideImageApi() : ImageApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepository(
        imageApi : ImageApi
    ) : ImageRepository = ImageRepositoryImpl(imageApi)

}