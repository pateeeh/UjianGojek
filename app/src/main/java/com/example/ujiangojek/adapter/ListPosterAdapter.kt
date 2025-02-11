package com.example.ujiangojek.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ujiangojek.FoodDetailActivity
import com.example.ujiangojek.PromoCodeActivity
import com.example.ujiangojek.R
import com.example.ujiangojek.dataclass.FoodTopRate
import com.example.ujiangojek.dataclass.GoMartProduk
import com.example.ujiangojek.dataclass.Poster

class ListPosterAdapter(private val listPoster : ArrayList<Poster>): RecyclerView.Adapter<ListPosterAdapter.ListViewHolder>() {

    private lateinit var onItemCallBack: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Poster)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemCallBack = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_poster)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPosterAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.poster_row, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListPosterAdapter.ListViewHolder, position: Int) {
        val(image) = listPoster[position]
        holder.imgPhoto.setImageResource(image)

        holder.itemView.setOnClickListener{
            val poster = Intent(holder.itemView.context, PromoCodeActivity::class.java)
            holder.itemView.context.startActivity(poster)
        }
    }

    override fun getItemCount(): Int = listPoster.size
}