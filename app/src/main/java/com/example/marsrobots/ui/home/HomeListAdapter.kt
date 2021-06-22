package com.example.marsrobots.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marsrobots.R
import com.example.marsrobots.constants.OUTPUT_DATE_FORMAT
import com.example.marsrobots.constants.TIME_FORMAT
import com.example.marsrobots.databinding.HomeItemBinding
import com.example.marsrobots.network.response.Item
import com.example.marsrobots.room.ImageEntity
import com.example.marsrobots.utils.Helper
import com.example.marsrobots.utils.dpTOpx
import com.example.marsrobots.utils.getDeviceWidth
import kotlinx.android.synthetic.main.home_item.view.*


class HomeListAdapter(private val context: Context) :
    ListAdapter<ImageEntity, HomeListAdapter.ItemViewHolder>(DiffCallback()) {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, imageEntity: ImageEntity) = with(itemView) {
            var width = (context.getDeviceWidth() / 2 - context.dpTOpx(12f)).toInt()
            itemView.layoutParams.width = width
            itemView.image.layoutParams.width = width
            itemView.image.layoutParams.height = (width * 0.8).toInt()
            Glide
                .with(context)
                .load(imageEntity.url)
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .into(itemView.image)
            itemView.text_description.text =imageEntity.description
            itemView.text_date.text =
                Helper.convertDateToOutputFormat(
                   imageEntity.date,
                    TIME_FORMAT,
                    OUTPUT_DATE_FORMAT
                )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(context, getItem(position))
    }
}

class DiffCallback : DiffUtil.ItemCallback<ImageEntity>() {
    override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
        return oldItem == newItem
    }
}