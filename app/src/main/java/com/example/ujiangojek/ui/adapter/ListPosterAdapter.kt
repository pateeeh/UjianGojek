package com.example.ujiangojek.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ujiangojek.data.response.PosterItem
import com.example.ujiangojek.data.response.ProdukItem
import com.example.ujiangojek.databinding.PosterRowBinding

class ListPosterAdapter: ListAdapter<PosterItem, ListPosterAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: PosterRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: PosterItem) {
            Glide.with(binding.root.context)
                .load(dataItem.fotoPoster)
                .into(binding.imgPoster)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = getItem(position)
        holder.bind(dataItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PosterRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PosterItem>() {
            override fun areContentsTheSame(oldItem: PosterItem, newItem: PosterItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: PosterItem, newItem: PosterItem): Boolean {
                return oldItem == newItem
            }

        }
    }


}