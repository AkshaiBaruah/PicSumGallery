package com.entity.picsumgallery.presentation.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.entity.picsumgallery.MainViewModel
import com.entity.picsumgallery.R
import com.entity.picsumgallery.databinding.FragmentImageListBinding
import com.entity.picsumgallery.domain.model.ImageItem
import com.entity.picsumgallery.presentation.adapter.ImagePagingAdapter


class ImageListFragment : Fragment() {

    private var _binding: FragmentImageListBinding? = null
    private val binding: FragmentImageListBinding
        get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private lateinit var madapter : ImagePagingAdapter
    private lateinit var imageSliderFragment: ImageSliderFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectUiState()

//        madapter.setOnItemClickListener { selectedItem ->
//            val galleryFragment = GalleryFragment.newInstance(selectedItem)
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainer, galleryFragment)
//                .addToBackStack(null)
//                .commit()
//        }

    }
//    class onClick : ImagePagingAdapter.ImageItemClicked{
//        override fun onItemClicked(item: ImageItem) {
//            Log.d("Test" , "done")
//        }
//    }
    private fun initView() {
    val span : Int
    val currentOrientation = resources.configuration.orientation
    if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
        span = 5
    } else {
        span = 3
    }
        val _layoutManager = GridLayoutManager(requireContext(), span)
        madapter = ImagePagingAdapter({
            moveToImageSlider(it)
        })
        binding.imageListRv.layoutManager = _layoutManager
        //binding.imageListRv.setHasFixedSize(true)         //this is causing a crash
        binding.imageListRv.adapter = madapter
        imageSliderFragment = ImageSliderFragment()
    }

    private fun collectUiState() {
        mainViewModel =  ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
//        lifecycleScope.launch {
//            mainViewModel.images.collect{
//                madapter.submitData(lifecycle,it)
//            }
//        }

        mainViewModel.images.observe(viewLifecycleOwner , Observer {data->
            madapter.submitData(lifecycle , data)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //madapter = null
        _binding = null
    }
    fun moveToImageSlider(item : ImageItem){
        Log.d("Test" , "done")
        val bundle = Bundle().apply {
            putParcelable("IMAGE_ITEM", item)
        }
        imageSliderFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment_container , imageSliderFragment)
            .addToBackStack("frag")
            .commit()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("recycler_state", binding.imageListRv.layoutManager?.onSaveInstanceState())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val savedRecyclerState = savedInstanceState?.getParcelable<Parcelable>("recycler_state")
        binding.imageListRv.layoutManager?.onRestoreInstanceState(savedRecyclerState)
    }
}