package com.example.photoeditor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.photoeditor.ApiFactory.photosService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    val photosAdapter = PhotosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setupUi()

        getPhotos()
    }

    private fun getPhotos() {
        launch {
            val photos = photosService().fetchPhotos()
            photosAdapter.submitList(photos)
        }
    }

    private fun setupUi() {
        recycler_view_photos.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view_photos.adapter = photosAdapter
    }


}
