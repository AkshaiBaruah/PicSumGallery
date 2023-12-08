package com.entity.picsumgallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.entity.picsumgallery.domain.repository.ImageRepository
import com.entity.picsumgallery.domain.useCases.imageUseCases.ImageUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    imageUseCases: ImageUseCases
) : ViewModel() {

    val images = imageUseCases.getImages().cachedIn(viewModelScope)
}