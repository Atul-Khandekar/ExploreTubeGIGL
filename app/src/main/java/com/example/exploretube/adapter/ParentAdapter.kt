package com.example.exploretube.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.exploretube.R
import com.example.exploretube.base.BaseAdapter
import com.example.exploretube.model.local.HomeData

class ParentAdapter : BaseAdapter<HomeData>(ParentDiffUtil()) {

    class ParentDiffUtil : DiffUtil.ItemCallback<HomeData>() {
        override fun areItemsTheSame(oldItem: HomeData, newItem: HomeData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeData, newItem: HomeData): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position==0) {
            return R.layout.item_horizontal_parent_rv
        } else {
            return R.layout.item_vertical_rv
        }
    }
}