package com.sk.shubhamgoyalsbnri.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sk.shubhamgoyalsbnri.R
import com.sk.shubhamgoyalsbnri.databinding.ItemViewBinding
import com.sk.shubhamgoyalsbnri.model.User


//
// Created by SK(Sk) on 05/06/20.
// Copyright (c) 2020 Sktech. All rights reserved.

class Adapter : PagedListAdapter<User, Adapter.ViewHolder>(User.CALLBACK) {


    inner class ViewHolder : RecyclerView.ViewHolder {

        private val binding: ItemViewBinding

        constructor(binding: ItemViewBinding) : super(binding.root) {
            this.binding = binding
        }

        fun setModel(user: User) {
            binding.model = user
            binding.executePendingBindings()
        }

    }

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.getContext())
        val binding = DataBindingUtil.inflate<ItemViewBinding>(
            layoutInflater,
            R.layout.item_view,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.setModel(it) }

    }
}