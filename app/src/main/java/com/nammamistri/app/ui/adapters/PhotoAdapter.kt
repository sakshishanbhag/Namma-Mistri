package com.nammamistri.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nammamistri.app.data.entity.SitePhoto
import com.nammamistri.app.databinding.ItemPhotoBinding

class PhotoAdapter : ListAdapter<SitePhoto, PhotoAdapter.PhotoViewHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: SitePhoto) {
            Glide.with(itemView.context)
                .load(photo.filePath)
                .into(binding.photoImage)

            binding.photoDate.text = photo.capturedDate
        }
    }

    class PhotoDiffCallback : DiffUtil.ItemCallback<SitePhoto>() {
        override fun areItemsTheSame(oldItem: SitePhoto, newItem: SitePhoto) =
            oldItem.photoId == newItem.photoId

        override fun areContentsTheSame(oldItem: SitePhoto, newItem: SitePhoto) =
            oldItem == newItem
    }
}
