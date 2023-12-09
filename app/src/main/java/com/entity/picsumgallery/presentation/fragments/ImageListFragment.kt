package com.entity.picsumgallery.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.entity.picsumgallery.MainViewModel
import com.entity.picsumgallery.databinding.FragmentImageListBinding
import com.entity.picsumgallery.presentation.adapter.ImagePagingAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ImageListFragment : Fragment() {

    private var _binding: FragmentImageListBinding? = null
    private val binding: FragmentImageListBinding
        get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private lateinit var madapter : ImagePagingAdapter

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
    }

    private fun initView() {
        val _layoutManager = GridLayoutManager(requireContext(), 3)
        madapter = ImagePagingAdapter()
        binding.imageListRv.layoutManager = _layoutManager
        //binding.imageListRv.setHasFixedSize(true)         //this is causing a crash
        binding.imageListRv.adapter = madapter
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
}