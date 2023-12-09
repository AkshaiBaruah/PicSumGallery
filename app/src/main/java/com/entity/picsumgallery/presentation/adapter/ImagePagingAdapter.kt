package com.entity.picsumgallery.presentation.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.entity.picsumgallery.R
import com.entity.picsumgallery.domain.model.ImageItem

class ImagePagingAdapter(
    private val onItemClicked : (item : ImageItem) -> Unit
)
    : PagingDataAdapter<ImageItem, ImagePagingAdapter.ImageViewHolder>(COMPARATOR) {

    inner class ImageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.item_image)
        val author = itemView.findViewById<TextView>(R.id.item_text)
//        init {
//            itemView.setOnClickListener {
//                onItemClickListener
//            }
//        }
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currItem = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(getItem(position)!!)
        }
        if(currItem != null){
            holder.author.text = currItem.author
            Glide.with(holder.itemView.context)
                .load(currItem.download_url)
                .into(holder.image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item , parent, false)
        val holder = ImageViewHolder(view)
        return holder
    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<ImageItem>(){
            override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ImageItem,
                newItem: ImageItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


}

