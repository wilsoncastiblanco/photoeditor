package com.example.photoeditor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_photo_edited.*

class PhotoEditedFragment : Fragment() {

    private val args: PhotoEditedFragmentArgs by navArgs()

    private val adapter = PhotoChunksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_edited, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        val chunkImages = args.photoEdited
        adapter.submitList(chunkImages.toList())
    }

    private fun setupUi() {
        recycler_view_chunck.layoutManager = GridLayoutManager(context, 4)
        recycler_view_chunck.adapter = adapter
    }

}
