package com.example.photoeditor

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.photo_item.view.*


class PhotoChunksAdapter : ListAdapter<Bitmap, PhotoChunksAdapter.PhotoChunksViewHolder>(PhotoChunksDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoChunksViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.photo_chunk_item, parent, false)
        return PhotoChunksViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoChunksViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }

    inner class PhotoChunksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Bitmap) {
            Glide
                .with(itemView.context)
                .load(photo)
                .placeholder(R.drawable.ic_placeholder_image)
                .into(itemView.image_view_photo)
        }
    }
}

class PhotoChunksDiffCallBack : DiffUtil.ItemCallback<Bitmap>() {
    override fun areItemsTheSame(oldItem: Bitmap, newItem: Bitmap): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: Bitmap, newItem: Bitmap): Boolean {
        return false
    }

}
