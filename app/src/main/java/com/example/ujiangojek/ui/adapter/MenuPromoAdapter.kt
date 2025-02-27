package com.example.ujiangojek.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ujiangojek.R
import com.example.ujiangojek.dataclass.MenuPromo


class MenuPromoAdapter(private val listProduk : ArrayList<MenuPromo>): RecyclerView.Adapter<MenuPromoAdapter.ListViewHolder>() {

    private lateinit var onItemCallBack: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: MenuPromo)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemCallBack = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMenu: TextView = itemView.findViewById(R.id.tv_menu)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.promo_menu_row, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(namaMenu) = listProduk[position]
        holder.tvMenu.text = namaMenu
        
    }



    override fun getItemCount(): Int = listProduk.size
}