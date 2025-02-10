package com.example.ujiangojek.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ujiangojek.FoodDetailActivity
import com.example.ujiangojek.R
import com.example.ujiangojek.dataclass.FoodTopRate

class ListFoodTopRateAdapter(private val listFood : ArrayList<FoodTopRate>): RecyclerView.Adapter<ListFoodTopRateAdapter.ListViewHolder>() {

    private lateinit var onItemCallBack: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: FoodTopRate)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemCallBack = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_produk)
        val tvName: TextView = itemView.findViewById(R.id.tv_nama_toko)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListFoodTopRateAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.foodtoprate_row, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListFoodTopRateAdapter.ListViewHolder, position: Int) {
        val(image, namaToko) = listFood[position]
        holder.imgPhoto.setImageResource(image)
        holder.tvName.text = namaToko

        holder.itemView.setOnClickListener {
            val foodDetail = Intent(holder.itemView.context, FoodDetailActivity::class.java)
            holder.itemView.context.startActivity(foodDetail)
        }
    }

    override fun getItemCount(): Int = listFood.size
}