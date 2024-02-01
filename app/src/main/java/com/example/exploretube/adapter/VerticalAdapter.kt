package com.example.exploretube.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.exploretube.R
import com.example.exploretube.base.BaseAdapter
import com.example.exploretube.model.local.Videos

class VerticalAdapter : BaseAdapter<Videos>(VerticalDiffUtil()) {

    class VerticalDiffUtil : DiffUtil.ItemCallback<Videos>() {
        override fun areItemsTheSame(oldItem: Videos, newItem: Videos): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Videos, newItem: Videos): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int): Int {
            return R.layout.item_vertical_rv
    }

}