package com.example.exploretube.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.exploretube.R
import com.example.exploretube.base.BaseAdapter
import com.example.exploretube.model.local.Media

class VerticalAdapter : BaseAdapter<Media>(VerticalDiffUtil()) {

    class VerticalDiffUtil : DiffUtil.ItemCallback<Media>() {
        override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int): Int {
            return R.layout.item_vertical_rv
    }

}