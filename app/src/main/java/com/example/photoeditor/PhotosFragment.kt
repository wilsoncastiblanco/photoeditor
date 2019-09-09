package com.example.photoeditor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_photos.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class PhotosFragment : Fragment(), CoroutineScope by MainScope() {

    private val photosAdapter = PhotosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()

        getPhotos()
    }

    private fun getPhotos() {
        launch {
            val photos = ApiFactory.photosService().fetchPhotos()
            photosAdapter.submitList(photos)
        }
    }

    private fun setupUi() {
        recycler_view_photos.layoutManager = LinearLayoutManager(context)
        recycler_view_photos.adapter = photosAdapter
    }

}
