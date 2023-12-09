package com.entity.picsumgallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.entity.picsumgallery.presentation.adapter.ImagePagingAdapter
import com.entity.picsumgallery.presentation.fragments.ImageListFragment
import com.entity.picsumgallery.presentation.fragments.ImageSliderFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //lateinit var mainrv : RecyclerView
    //lateinit var adapter: ImagePagingAdapter
    //lateinit var mainViewModel: MainViewModel
    lateinit var imageListFragment : ImageListFragment
    lateinit var imageSliderFragment: ImageSliderFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //mainrv = findViewById(R.id.mainrv)
        //adapter = ImagePagingAdapter()
        //mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

//        mainrv.layoutManager = GridLayoutManager(this , 3)
//        mainrv.setHasFixedSize(true)
//        mainrv.adapter = adapter

//        mainViewModel.images.observe(this , Observer {
//            adapter.submitData(lifecycle , it)
//        })

        setSupportActionBar(findViewById(R.id.toolbar))

        imageListFragment = ImageListFragment()
        imageSliderFragment = ImageSliderFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_container , imageListFragment)
            commit()
        }

    }

    fun navigateToSlider() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment_container, imageSliderFragment)
        transaction.addToBackStack(null) // Optional: Adds the transaction to the back stack
        transaction.commit()
    }
}