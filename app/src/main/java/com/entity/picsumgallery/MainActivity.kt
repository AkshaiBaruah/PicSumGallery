package com.entity.picsumgallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.entity.picsumgallery.presentation.adapter.ImagePagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mainrv : RecyclerView
    lateinit var adapter: ImagePagingAdapter
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainrv = findViewById(R.id.mainrv)
        adapter = ImagePagingAdapter()
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainrv.layoutManager = GridLayoutManager(this , 3)
        mainrv.setHasFixedSize(true)
        mainrv.adapter = adapter

        mainViewModel.images.observe(this , Observer {
            adapter.submitData(lifecycle , it)
        })


    }
}