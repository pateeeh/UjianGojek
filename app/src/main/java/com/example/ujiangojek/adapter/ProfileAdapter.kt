package com.example.ujiangojek.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ujiangojek.R
import com.example.ujiangojek.databinding.ItemProfileBinding
import com.example.ujiangojek.dataclass.ProfileItem

class ProfileAdapter(private val items: List<ProfileItem>) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemProfileBinding.bind(view)

        fun bind(item: ProfileItem) {
            binding.ivIcon.setImageResource(item.icon)
            binding.tvTitle.text = item.title
            binding.tvSubtitle.text = item.subtitle

            if (item.subtitle.isNullOrEmpty()) {
                binding.tvSubtitle.visibility = View.GONE
            } else {
                binding.tvSubtitle.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}