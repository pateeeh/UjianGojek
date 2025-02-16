package com.example.ujiangojek

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ujiangojek.adapter.ListMartProdukAdapter
import com.example.ujiangojek.databinding.ActivityProductDetailBinding
import com.example.ujiangojek.dataclass.GoMartProduk

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var rvSimilarProduct: RecyclerView
    private val list = ArrayList<GoMartProduk>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = binding.imgProduct

        val imageResourceId = intent.getIntExtra("KEY_IMAGE", 0)
        val namaProduk = intent.getStringExtra("KEY_PRODUK")
        val harga = intent.getStringExtra("KEY_HARGA")

        if (namaProduk != null) {
            binding.tvProduct.text = namaProduk
        }
        if (harga != null) {
            binding.tvHarga.text = harga
        }

        image.setImageResource(imageResourceId)

        binding.btnBack.setOnClickListener {
            finish()
        }


        rvSimilarProduct = binding.rvSimilarProduct
        rvSimilarProduct.setHasFixedSize(true)
        list.addAll(getListProduct())
        showRecyclerList()
    }

    private fun getListProduct(): ArrayList<GoMartProduk> {
        val image = resources.obtainTypedArray(R.array.data_img_gomart)
        val namaBarang = resources.getStringArray(R.array.data_produk_gomart)
        val harga = resources.getStringArray(R.array.data_harga_gomart)

        val listProduct = ArrayList<GoMartProduk>()
        for (i in namaBarang.indices) {
            val produk = GoMartProduk(image.getResourceId(i, -1), namaBarang[i], harga[i])
            listProduct.add(produk)
        }
        return listProduct
    }

    private fun showRecyclerList() {
        rvSimilarProduct.layoutManager = LinearLayoutManager(this@ProductDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        val listProductAdapter = ListMartProdukAdapter(list)
        rvSimilarProduct.adapter = listProductAdapter
    }
}
