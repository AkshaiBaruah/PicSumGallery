package com.entity.picsumgallery.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.entity.picsumgallery.R
import com.entity.picsumgallery.databinding.FragmentImageListBinding
import com.entity.picsumgallery.databinding.FragmentImageSliderBinding
import com.entity.picsumgallery.domain.model.ImageItem


class ImageSliderFragment : Fragment() {

    private var imageItem: ImageItem?=null
    private var _binding: FragmentImageSliderBinding? = null
    private val binding: FragmentImageSliderBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageSliderBinding.inflate(inflater, container, false)
        return _binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageItem = arguments?.getParcelable<ImageItem>("IMAGE_ITEM")

        initView()
    }
    fun initView(){
        binding.sliderAuthor.text = imageItem?.author
        val url = imageItem?.download_url
        Glide.with(requireContext())
            .load(url)
            .into(binding.sliderImage)
        binding.sliderAuthor.setTypeface(ResourcesCompat.getFont(requireContext() , R.font.creattion_demo))
    }



}