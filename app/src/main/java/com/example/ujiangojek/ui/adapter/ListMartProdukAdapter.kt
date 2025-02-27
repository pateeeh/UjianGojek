package com.example.ujiangojek.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ujiangojek.data.response.ProdukItem
import com.example.ujiangojek.databinding.GomartRowBinding
import com.example.ujiangojek.ui.fragmenthome.ProductDetailActivity

class ListMartProdukAdapter: androidx.recyclerview.widget.ListAdapter<ProdukItem, ListMartProdukAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: GomartRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: ProdukItem) {
            Glide.with(binding.root.context)
                .load(dataItem.gambar)
                .into(binding.imgBarang)
            binding.tvNamaBarang.text = dataItem.namaProduk
            binding.tvHarga.text = dataItem.harga

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GomartRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = getItem(position)
        holder.bind(dataItem)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, ProductDetailActivity::class.java)
            intent.putExtra("PRODUK_DATA", dataItem)
            holder.itemView.context.startActivity(intent)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProdukItem>() {
            override fun areContentsTheSame(oldItem: ProdukItem, newItem: ProdukItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: ProdukItem, newItem: ProdukItem): Boolean {
                return oldItem == newItem
            }

        }
    }


}