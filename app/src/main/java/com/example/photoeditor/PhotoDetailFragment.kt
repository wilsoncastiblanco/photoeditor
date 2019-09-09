package com.example.photoeditor

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_photo_detail.*
import kotlin.math.sqrt


class PhotoDetailFragment : Fragment() {

    private val args: PhotoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            Glide
                .with(it)
                .load(args.photo.urls.full)
                .into(image_view_photo)
        }

        button_process_image.setOnClickListener {
            splitImage(image_view_photo, 16)
        }
    }

    private fun splitImage(image: ImageView, chunkNumbers: Int) {

        //For the number of rows and columns of the grid to be displayed
        val rows: Int
        val cols: Int = sqrt(chunkNumbers.toDouble()).toInt()

        //For height and width of the small image chunks
        val chunkHeight: Int
        val chunkWidth: Int

        //To store all the small image chunks in bitmap format in this list
        val chunkedImages = ArrayList<Bitmap>(chunkNumbers)

        //Getting the scaled bitmap of the source image
        val drawable = image.drawable as BitmapDrawable
        val bitmap = drawable.bitmap
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width, bitmap.height, true)

        rows = cols
        chunkHeight = bitmap.height / rows
        chunkWidth = bitmap.width / cols

        //xCoord and yCoord are the pixel positions of the image chunks
        var yCoord = 0
        for (x in 0 until rows) {
            var xCoord = 0
            for (y in 0 until cols) {
                chunkedImages.add(
                    Bitmap.createBitmap(
                        scaledBitmap,
                        xCoord,
                        yCoord,
                        chunkWidth,
                        chunkHeight
                    )
                )
                xCoord += chunkWidth
            }
            yCoord += chunkHeight
        }

        val direction = PhotoDetailFragmentDirections.actionPhotoDetailFragmentToPhotoEditedFragment(chunkedImages.toTypedArray())
        findNavController().navigate(direction)
    }

}
