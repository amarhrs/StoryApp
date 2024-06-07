package com.amar.storyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amar.storyapp.R
import com.amar.storyapp.data.remote.response.ListStoryItem
import com.amar.storyapp.databinding.StoryItemBinding
import com.bumptech.glide.Glide

class StoryAdapter : PagingDataAdapter<ListStoryItem, StoryAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((ListStoryItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = StoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!, onItemClick)
    }

    class ListViewHolder(private var binding: StoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListStoryItem, onItemClick: ((ListStoryItem) -> Unit)?) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(item.photoUrl)
                    .error(R.drawable.baseline_broken_image_24)
                    .into(ivItemPhoto)
                tvItemName.text = item.name
                tvItemDescription.text = item.description

                itemView.setOnClickListener {
                    onItemClick?.invoke(item)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListStoryItem,
                newItem: ListStoryItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}