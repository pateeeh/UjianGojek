package com.example.ujiangojek.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ujiangojek.data.response.ListInboxResponse
import com.example.ujiangojek.data.response.ListInboxResponseItem
import com.example.ujiangojek.databinding.CardInboxBinding

class InboxAdapter : ListAdapter<ListInboxResponseItem, InboxAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(private val binding: CardInboxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: ListInboxResponseItem) {
            binding.tvTagline.text = dataItem.tagline
            binding.tvDescPromo.text = dataItem.deskripsiPromo
            Glide.with(binding.root.context)
                .load(dataItem.fotoPoster)
                .into(binding.imgPoster)
            Glide.with(binding.root.context)
                .load(dataItem.icon)
                .into(binding.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardInboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = getItem(position)
        holder.bind(dataItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListInboxResponseItem>() {
            override fun areContentsTheSame(oldItem: ListInboxResponseItem, newItem: ListInboxResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ListInboxResponseItem, newItem: ListInboxResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
