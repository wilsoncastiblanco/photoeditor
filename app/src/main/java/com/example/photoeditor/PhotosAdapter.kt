package com.example.photoeditor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.photo_item.view.*


class PhotosAdapter : ListAdapter<Photo, PhotosAdapter.PhotoViewHolder>(PhotoDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo) {
            Glide
                .with(itemView.context)
                .load(photo.urls.regular)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder_image)
                .into(itemView.image_view_photo)

            itemView.setOnClickListener {
                val direction = PhotosFragmentDirections.actionPhotosFragmentToPhotoDetailFragment(photo)
                it.findNavController().navigate(direction)
            }
        }
    }
}

class PhotoDiffCallBack : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

}
