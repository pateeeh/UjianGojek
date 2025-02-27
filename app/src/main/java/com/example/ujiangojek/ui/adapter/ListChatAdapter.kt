package com.example.ujiangojek.ui.adapter
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ujiangojek.data.response.ListUserResponseItem
import com.example.ujiangojek.databinding.ChatCardBinding
import com.example.ujiangojek.ui.fragmentchat.ChatActivity

class ListChatAdapter: ListAdapter<ListUserResponseItem, ListChatAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ChatCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: ListUserResponseItem) {
            Glide.with(binding.root.context)
                .load(dataItem.foto)
                .into(binding.avatar)
            binding.tvNama.text = dataItem.nama
            binding.tvChat.text = dataItem.email
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListChatAdapter.ViewHolder {
        val binding = ChatCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = getItem(position)
        holder.bind(dataItem)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, ChatActivity::class.java)
            intent.putExtra("USER_DATA", dataItem)
            holder.itemView.context.startActivity(intent)
        }

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListUserResponseItem>() {
            override fun areContentsTheSame(oldItem: ListUserResponseItem, newItem: ListUserResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ListUserResponseItem, newItem: ListUserResponseItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}