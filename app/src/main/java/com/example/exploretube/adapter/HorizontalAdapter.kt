package com.example.exploretube.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.exploretube.R
import com.example.exploretube.base.BaseAdapter
import com.example.exploretube.model.local.Photos

class HorizontalAdapter : BaseAdapter<Photos>(HorizontalDiffUtil()) {

    class HorizontalDiffUtil : DiffUtil.ItemCallback<Photos>() {
        override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_horizontal_rv
    }
}